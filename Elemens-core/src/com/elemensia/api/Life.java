package com.elemensia.api;

public class Life {

	private int healthPoint;
	private int maxHealthPoint;

	public Life(int health) {
		this.healthPoint = health;
		this.maxHealthPoint = health;
	}

	public boolean isDead(){
		return (this.healthPoint <= 0);
	}
	
	public void relive(){
		this.healthPoint = this.maxHealthPoint;
	}
	
	@Override
	public String toString() {
		return this.healthPoint + " / " + this.maxHealthPoint;
	}
	
}
