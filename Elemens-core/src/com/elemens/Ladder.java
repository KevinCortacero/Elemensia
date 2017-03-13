package com.elemens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Ladder extends GameObject{
	
	Solid top;
	Rectangle climbZone;
	
	public Ladder(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.top = new Solid(x - 20, y + height -4, width + 40, 4);
		this.climbZone = new Rectangle(x + 20, y, width - 40, height - 10);
	}
	
	public void draw(ShapeRenderer sr) {
		sr.rect(this.body.x, this.body.y, this.body.width, this.body.height, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY);
		sr.rect(this.climbZone.x, this.climbZone.y, this.climbZone.width, this.climbZone.height, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN);
		this.top.draw(sr, Color.RED);
	}

}
