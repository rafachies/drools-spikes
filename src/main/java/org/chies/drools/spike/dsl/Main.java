package org.chies.drools.spike.dsl;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

public class Main {

	public static void main(String[] args) {
		try {
			StatefulKnowledgeSession statefulSession = createStatefulSession();
			InsuranceProposal insuranceProposal = createFact();
			insertFactAndFireRules(statefulSession, insuranceProposal);
			System.out.println("score: " + insuranceProposal.getScore());
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	private static void insertFactAndFireRules(StatefulKnowledgeSession statefulSession, InsuranceProposal insuranceProposal) {
		statefulSession.insert(insuranceProposal);
		statefulSession.fireAllRules();
	}

	private static InsuranceProposal createFact() {
		InsuranceProposal insuranceProposal = new InsuranceProposal();
		insuranceProposal.setAge(30);
		insuranceProposal.setLocale(Locale.CAPITAL);
		insuranceProposal.setCarType(CarType.POPULAR);
		insuranceProposal.setMarried(true);
		insuranceProposal.setScpcConstraint(false);
		return insuranceProposal;
	}
	
	private static StatefulKnowledgeSession createStatefulSession() throws Exception {
		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		knowledgeBuilder.add(new ClassPathResource("dsl/insuranceProposal.dsl"), ResourceType.DSL);
		knowledgeBuilder.add(new ClassPathResource("dsl/ageRules.dslr"), ResourceType.DSLR);
		knowledgeBuilder.add(new ClassPathResource("dsl/localeRules.dslr"), ResourceType.DSLR);
		knowledgeBuilder.add(new ClassPathResource("dsl/carRules.dslr"), ResourceType.DSLR);
		knowledgeBuilder.add(new ClassPathResource("dsl/constraintRules.dslr"), ResourceType.DSLR);
		knowledgeBuilder.add(new ClassPathResource("dsl/statusRules.dslr"), ResourceType.DSLR);
		KnowledgeBase knowledgeBase = knowledgeBuilder.newKnowledgeBase();
		return knowledgeBase.newStatefulKnowledgeSession();
	}
}
