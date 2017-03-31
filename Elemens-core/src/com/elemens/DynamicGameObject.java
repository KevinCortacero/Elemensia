package com.elemens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public abstract class DynamicGameObject extends GameObject implements Disposable {
	
	private WaterAbility waterAbility;
	protected CollideManager collideManager;
	protected float velocityY;
	private int jumpCount;
	private String state;
	private SpriteAnimation sprite;

	public DynamicGameObject(int x, int y, int width, int height, String[] states, String spritePath) {
		super(x, y, width, height);
		this.sprite = new SpriteAnimation(width, height, states,spritePath);
		this.state = states[0];
		this.setState(0);
		this.velocityY = 0;
		this.jumpCount = 1;
		this.collideManager = new CollideManager(x, y, width, height);
		this.waterAbility = new WaterAbility(x, y, width, height);
	}
	
	public void update(){
		this.waterAbility.update();
		
	}
	
	public void draw(ShapeRenderer sr) {
		this.body.draw(sr, Color.GOLD);
		this.collideManager.draw(sr);
		this.waterAbility.draw(sr);
	}
	
	public void updateAnimation(float delta){
		this.sprite.update(delta);
	}
	
	private void setState(int i) {
		if (!this.state.equals(Hero.STATES[i])){
			this.state = Hero.STATES[i];
			this.sprite.reset();
		}
	}

	public boolean isMovingUp() {
		return (this.velocityY > 0); 
	}

	public void stopV(float height) {
		this.velocityY = 0;
		this.setPosition(this.body.x, height);
	}

	public void stopH(float width) {
		this.setPosition(width, this.body.y);
	}

	public void setPosition(float x, float y) {
		this.body.x = x;
		this.body.y = y;
		this.collideManager.setPosition(x, y);
		this.waterAbility.setPosition(x, y);
	}

	public void moveRight(float delta) {
		this.body.x += (250 * delta);
		this.setState(2);
	}

	public void moveLeft(float delta) {
		this.body.x -= (250 * delta);
		this.setState(3);
	}

	public void climbUp() {
		this.velocityY = 5;
		this.setState(8);
	}

	public void climbDown() {
		this.velocityY = -5;
		this.setState(8);
	}

	public void jump() {
		if (this.jumpCount > 0) {
			this.velocityY = 10;
			this.jumpCount--;
			this.setState(4);
		}
	}

	public void resetJump() {
		this.jumpCount = 1;
	}

	public void draw(SpriteBatch batch, float delta) {
		batch.draw(this.sprite.getCurrentAnimation(this.state), this.body.x, this.body.y);
	}

	@Override
	public void dispose() {
		this.sprite.dispose();
	}

}
