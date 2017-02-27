package com.elemens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class DynamicGameObject extends GameObject implements Disposable{
	
	protected Sprite sprite;
	private float velocityY;
	private int jumpCount;
	
	public DynamicGameObject(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.sprite = new Sprite(new Texture("papyrus.png"));
		this.velocityY = 0;
		this.jumpCount = 1;
		this.sprite.setPosition(x, y);
	}
	
	public void stopV(float height){
		this.velocityY = 0;
		this.setPosition(this.body.x, height);
	}
	
	public void stopH(float width){
		this.setPosition(width, this.body.y);
	}
	
	public void setPosition(float x, float y) {
		this.body.x = x;
		this.body.y = y;
		this.sprite.setPosition(x, y);
	}

	public void moveRight(float delta){
		this.body.x += (250 * delta);
	}
	
	public void moveLeft(float delta){
		this.body.x -= (250 * delta);
	}
	
	public void climb(){
		this.velocityY = 5;
	}
	
	public void update(float delta){
		this.velocityY -= (25*delta);
		this.body.y += this.velocityY;
		this.setPosition(body.x, body.y);
	}
	
	public void jump(){
		if (this.jumpCount > 0){
			this.velocityY = 10;
			this.jumpCount --;
		}
	}
	
	public void resetJump(){
		this.jumpCount = 1;
	}

	public void draw(SpriteBatch batch){
		this.sprite.draw(batch);
	}
	
	@Override
	public void dispose(){
		this.sprite.getTexture().dispose();
	}

}
