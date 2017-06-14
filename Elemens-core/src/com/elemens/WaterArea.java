package com.elemens;

public class WaterArea extends Area{

	public WaterArea(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public boolean contains(DynamicGameObject object) {
		return this.getBody().overlaps(object.getWaterBox());
	}

}
