package com.elemensia.api;

public class Strain {

	private float max;
	private float current;

	public Strain(int max) {
		this.max = (float) max;
		this.current = this.max;
	}

	public boolean isKO() {
		return this.max > 0;
	}

	public int getRatio() {
		return (int) (this.max / this.current * 100);
	}

	public int increase(float value) {
		if (this.current > this.max) {
			this.current = this.max;
		}
		this.current += value;
		return this.getRatio();
	}

	public int increase(int value) {
		return this.increase((float) value);
	}

	public boolean decrease(float value) {
		this.current -= value;
		return this.isKO();
	}

	public boolean decrease(int value) {
		return this.decrease((float) value);
	}

	public int getMax() {
		return (int) max;
	}

	public int getCurrent() {
		return (int) current;
	}

	@Override
	public String toString() {
		return "Strain [max=" + max + ", current=" + current + "]";
	}

}
