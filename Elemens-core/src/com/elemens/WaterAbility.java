package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WaterAbility {

	public boolean isOnWater;
	public boolean isUnderWater;
	public CollideBox waterBox;
	
	public WaterAbility(int x, int y, int width, int height) { 
		this.waterBox = new CollideBox(x, 0, y , height*2/3, width, 20);
		this.isOnWater = false;
		this.isUnderWater = false;
	}

	public void setPosition(float x, float y) {
		this.waterBox.setPosition(x, y);
	}

	public void draw(ShapeRenderer sr) {
		this.waterBox.draw(sr, Color.ORANGE);
	}

	public void update(ArrayList<WaterArea> water, boolean isOnWater) {
		this.isOnWater = isOnWater;
		this.isUnderWater = isUnderWater(water);
	}

	private boolean isUnderWater(ArrayList<WaterArea> water) {
		for (WaterArea w : water) {
			if (this.waterBox.overlaps(w.getBody())){
				return true;
			}
		}
		return false;
	}
}
