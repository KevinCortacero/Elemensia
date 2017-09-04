package com.elemensia.api;

public class WaterArea extends Area{

	public WaterArea(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public boolean overlaps(CollideBox box) {
		return this.getBody().overlaps(box);
	}

}
