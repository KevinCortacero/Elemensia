package com.elemens;

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
}
