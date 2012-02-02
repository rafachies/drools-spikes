package org.chies.drools.spike.decisiontable;

import java.io.StringReader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.PackageBuilder;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.rule.Package;

public class Main {

	
	public static void main(String[] args) {
		try {
			WorkingMemory workingMemory = createWorkingMemory();
			TrafficViolation trafficViolationFact = createFact();
			insertFactAndFireRules(workingMemory, trafficViolationFact);
			System.out.println("Your penalty: " + trafficViolationFact.getPenalty());
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
		

	private static void insertFactAndFireRules(WorkingMemory workingMemory, TrafficViolation trafficViolationFact) {
		workingMemory.insert(trafficViolationFact);
		workingMemory.fireAllRules();
	}

	private static WorkingMemory createWorkingMemory() throws Exception {
		RuleBase ruleBase = readDecisionTable();
		WorkingMemory workingMemory = ruleBase.newStatefulSession();
		return workingMemory;
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

	private static RuleBase readDecisionTable() throws Exception {
		final SpreadsheetCompiler converter = new SpreadsheetCompiler();
		final String drlCompiled = converter.compile("/traffic-violation.xls", InputType.XLS);
		PackageBuilder builder = new PackageBuilder();
		builder.addPackageFromDrl( new StringReader( drlCompiled ) );
		Package droolsPackage = builder.getPackage();
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage(droolsPackage);
		return ruleBase;
	}
}
