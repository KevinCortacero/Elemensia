package com.elemensia.api;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Ladder extends GameObject{
	
	public Solid top;
	Rectangle climbZone;
	Rectangle climbZoneDown;
	
	public Ladder(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.top = new Solid(x - 20, y + height -4, width + 40, 4);
		this.climbZone = new Rectangle(x + 20, y, width - 40, height - 10);
		this.climbZoneDown = new Rectangle(x + 20, y + 120, width - 40, height -100);
	}
	
	public void draw(ShapeRenderer sr) {
		super.draw(sr);
		sr.rect(this.climbZone.x, this.climbZone.y, this.climbZone.width, this.climbZone.height, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN);
		sr.rect(this.climbZoneDown.x, this.climbZoneDown.y, this.climbZoneDown.width, this.climbZoneDown.height, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN);
		this.top.draw(sr);
	}

}
