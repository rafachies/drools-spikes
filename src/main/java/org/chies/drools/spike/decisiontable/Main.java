package org.chies.drools.spike.decisiontable;

import java.net.URL;

import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

public class Main {

	private static final String AGENT_NAME = "agentName";

	public static void main(String[] args) {
		try {
			StatefulKnowledgeSession statefulSession = createStatefulSessionWithChangeset();
			TrafficViolation trafficViolationFact = createFact();
			insertFactAndFireRules(statefulSession, trafficViolationFact);
			System.out.println("Your penalty: " + trafficViolationFact.getPenalty());
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
	
	private static StatefulKnowledgeSession createStatefulSessionWithChangeset() {
		KnowledgeAgent knowledgeAgent = KnowledgeAgentFactory.newKnowledgeAgent(AGENT_NAME);
		URL changesetUrl = Thread.currentThread().getContextClassLoader().getResource("decisiontable/change-set.xml");
		knowledgeAgent.applyChangeSet(ResourceFactory.newUrlResource(changesetUrl));
		KnowledgeBase knowledgeBase = knowledgeAgent.getKnowledgeBase();
		ResourceFactory.getResourceChangeNotifierService().start();
		ResourceFactory.getResourceChangeScannerService().start();
		return knowledgeBase.newStatefulKnowledgeSession();
	}

	private static void insertFactAndFireRules(StatefulKnowledgeSession statefulSession, TrafficViolation trafficViolationFact) {
		statefulSession.insert(trafficViolationFact);
		statefulSession.fireAllRules();
	}

	private static TrafficViolation createFact() {
		TrafficViolation trafficViolationFact = new TrafficViolation();
		trafficViolationFact.setCarLicense(true);
		trafficViolationFact.setDriverDrunk(true);
		trafficViolationFact.setDriverLicense(true);
		trafficViolationFact.setLights(true);
		trafficViolationFact.setSpeed(170);
		return trafficViolationFact;
	}
	
	@SuppressWarnings("unused")
	private static StatefulKnowledgeSession createStatefulSession() throws Exception {
		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		knowledgeBuilder.add(new ClassPathResource("traffic-violation.xls"), ResourceType.DTABLE);
		KnowledgeBase knowledgeBase = knowledgeBuilder.newKnowledgeBase();
		return knowledgeBase.newStatefulKnowledgeSession();
	}
}
