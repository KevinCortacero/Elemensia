package com.elemens;

public abstract class GameObject {

	protected CollideBox body;
	
	public GameObject(int x, int y, int width, int height) {
		this.body = new CollideBox(x, 0, y, 0, width, height);
	}
	
}
