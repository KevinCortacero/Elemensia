package com.elemensia.api;

public class Organism {

	private Life life;
	private Hunger hunger;
	private Strain strain;

	public Organism(int maxLife, float regenLife, int maxHunger, float starvationHunger, int maxStrain) {
		this.life = new Life(maxLife, regenLife);
		this.hunger = new Hunger(maxHunger, starvationHunger);
		this.strain = new Strain(maxStrain);
	}

	public void live() {
		this.life.increase(this.life.getRegen());
		this.hunger.decrease(this.hunger.getStarvation());
	}

	@Override
	public String toString() {
		return "Organism [life=" + life + ", hunger=" + hunger + ", strain=" + strain + "]";
	}
	
}
