package com.elemensia.api;

public class Life {

	private float max;
	private float current;
	private float regen;

	public Life(int max, float regen) {
		this.max = (float) max;
		this.current = this.max;
		this.regen = regen;
	}

	public boolean isDead() {
		return (this.current <= 0);
	}

	public void revive() {
		this.current = this.max;
	}

	public int getRatio() {
		return (int) (this.max / this.current * 100);
	}

	// Return the amount of life gained by eating
	public int increase(float value) {
		this.current += value;
		if (this.current > this.max) {
			this.current = this.max;
		}
		return this.getRatio();
	}

	public int increase(int value) {
		return this.increase((float) value);
	}

	// Returns if dead or not
	public boolean decrease(float value) {
		this.current -= value;
		return this.isDead();
	}

	public boolean decrease(int value) {
		return this.decrease((float) value);
	}

	public float getMax() {
		return max;
	}

	public float getCurrent() {
		return current;
	}

	public float getRegen() {
		return regen;
	}

	@Override
	public String toString() {
		return "Life [max=" + max + ", current=" + current + ", regen=" + regen + "]";
	}

}
