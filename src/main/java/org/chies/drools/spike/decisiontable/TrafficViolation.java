package org.chies.drools.spike.decisiontable;

public class TrafficViolation {

	private int speed;
	private double penalty;
	private boolean lights;
	private boolean carLicense;
	private boolean driverDrunk;
	private boolean driverLicense;
	
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isCarLicense() {
		return carLicense;
	}
	public void setCarLicense(boolean carLicense) {
		this.carLicense = carLicense;
	}
	public boolean isDriverLicense() {
		return driverLicense;
	}
	public void setDriverLicense(boolean driverLicense) {
		this.driverLicense = driverLicense;
	}
	public boolean isLights() {
		return lights;
	}
	public void setLights(boolean lights) {
		this.lights = lights;
	}
	public boolean isDriverDrunk() {
		return driverDrunk;
	}
	public void setDriverDrunk(boolean driverDrunk) {
		this.driverDrunk = driverDrunk;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
}
