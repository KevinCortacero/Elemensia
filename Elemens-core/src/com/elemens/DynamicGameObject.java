package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
	
	public void update(float delta, Vector2 gravity, boolean canClimbUp, ArrayList<Solid> water){
		this.waterAbility.update(water, this.isOnWater(water));
		if (!canClimbUp && !this.waterAbility.isUnderWater){
			this.velocityY += (gravity.y*delta*2.5);
		}
		
		if (this.waterAbility.isUnderWater){
			this.velocityY *= 0.95;
			if (this.velocityY < 0.5 && this.velocityY > -0.5){
				this.resetJump();
			}
		}

		if (this.waterAbility.isOnWater){
			this.velocityY -= (gravity.y*delta*1.25);			
		}
		
		//  ANIMATION
		this.updateAnimation(delta);
		
		// GRAVITY
		this.body.y += this.velocityY;
		this.setPosition(body.x, body.y);
		
	}
	
	public void draw(ShapeRenderer sr) {
		this.body.draw(sr, Color.GOLD);
		this.collideManager.draw(sr);
		this.waterAbility.draw(sr);
	}
	
	public void updateAnimation(float delta){
		this.sprite.update(delta);
	}
	
	private boolean isOnWater(ArrayList<Solid> water) {
		for (Solid w : water) {
			if (this.body.overlaps(w.body)){
				return true;
			}

		}
		return false;
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

	public boolean isOnWater(Rectangle water) {
		return (this.body.overlaps(water));
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
