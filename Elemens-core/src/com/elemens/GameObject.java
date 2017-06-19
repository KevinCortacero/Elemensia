package com.elemens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class GameObject{

	private CollideBox body;
	
	public GameObject(int x, int y, int width, int height) {
		this.body = new CollideBox(x, 0, y, 0, width, height);
	}
	
	public CollideBox getBody(){
		return this.body;
	}

	public void draw(ShapeRenderer sr) {
		this.body.draw(sr, Color.GOLD);
	}
	
	public void setPosition(float x, float y){
		this.body.setPosition(x, y);
	}
	
	public float getX() {
		return this.body.x;
	}
	
	public float getY() {
		return this.body.y;
	}
	
	public void setX(float x) {
		this.body.x = x;
	}
	
	public void setY(float y) {
		this.body.y = y;
	}
	
	public float getWidth() {
		return this.body.width;
	}
	
	public float getHeight() {
		return this.body.height;
	}
}
