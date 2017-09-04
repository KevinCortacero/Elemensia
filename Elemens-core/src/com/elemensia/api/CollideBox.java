package com.elemensia.api;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class CollideBox extends Rectangle{

	private int x_offset;
	private int y_offset;
	
	public CollideBox(int x, int x_offset, int y,  int y_offset, int w, int h) {
		super(x + x_offset, y + y_offset, w, h);
		this.x_offset = x_offset;
		this.y_offset = y_offset;
	}
	
	public Rectangle setPosition(float x, float y) {
		return super.setPosition(x + this.x_offset, y + this.y_offset);
	}
	
	public void draw(ShapeRenderer sr, Color c) {
		sr.rect(this.x, this.y, this.width, this.height, c, c, c, c);
	}
}
