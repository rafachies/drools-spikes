package org.chies.drools.spike.dsl;

public class InsuranceProposal {

	private Integer age;
	private Locale locale;
	private CarType carType;
	private boolean married;
	private boolean scpcConstraint;
	
	private int score;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public boolean isScpcConstraint() {
		return scpcConstraint;
	}

	public void setScpcConstraint(boolean scpcConstraint) {
		this.scpcConstraint = scpcConstraint;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public void incrementScore(Integer value) {
		this.score += value;
	}

	public void decrementScore(Integer value) {
		this.score -= value;
	}
}
