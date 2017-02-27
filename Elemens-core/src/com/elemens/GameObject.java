package com.elemens;

import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {

	protected Rectangle body;
	
	public GameObject(int x, int y, int width, int height) {
		this.body = new Rectangle(x, y, width, height);
	}
	
}
