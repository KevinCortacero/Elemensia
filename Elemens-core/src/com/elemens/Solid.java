package com.elemens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Solid extends GameObject {

	public Solid(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void draw(ShapeRenderer sr, Color c) {
		sr.rect(this.body.x, this.body.y, this.body.width, this.body.height, c, c, c, c);
	}

}
