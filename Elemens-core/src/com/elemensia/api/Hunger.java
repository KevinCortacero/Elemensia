package com.elemensia.api;

public class Hunger {

	private int max;
	private int current;
	private float regen;
	
	private final float hpLost = (float) 0.25;

	public Hunger(int max, int current, float regen) {
		this.max = max;
		this.current = current;
		this.regen = regen;
	}

	public int getRatio() {
		return this.max / this.current;
	}

	// Return the amount of life gained by eating
	public int increase(int value) {
		this.current += value;
		if (this.current > this.max) {
			this.current = this.max;
		}
		return this.getRatio();
	}

	// Return the amount of life lost if hunger reaches 0
	public float decrease(int value) {
		float hpLost = 0;
		this.current -= value;
		if (this.current <= 0) {
			hpLost = this.hpLost;
		}
		return hpLost;
	}
	
	public int getMax() {
		return max;
	}

	public int getCurrent() {
		return current;
	}

	public float getRegen() {
		return regen;
	}
}
