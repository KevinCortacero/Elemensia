package com.elemens;

import java.util.ArrayList;

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

	public DynamicGameObject(int x, int y, int width, int height, SpriteAnimation sprite) {
		super(x, y, width, height);
		this.sprite = sprite;
		this.state = sprite.getDefaultState();
		this.setState(0);
		this.velocityY = 0;
		this.jumpCount = 1;
		this.collideManager = new CollideManager(x, y, width, height);
		this.waterAbility = new WaterAbility(x, y, width, height);
	}
	
	public void update(float delta, Vector2 gravity, boolean canClimbUp, ArrayList<WaterArea> water){
		this.waterAbility.update(water, this.isOnWater(water));
		
		// normal gravity
		if (!canClimbUp && !this.waterAbility.isUnderWater){
			this.velocityY += (gravity.y*delta*3);
		}
		
		// under water
		if (this.waterAbility.isUnderWater){
			this.velocityY *= 0.95;
			if (this.velocityY < 0.5 && this.velocityY > -0.5){
				this.resetJump();
			}
		}

		// on water
		if (this.waterAbility.isOnWater){
			this.velocityY -= (gravity.y*delta*1.25);			
		}
		
		//  ANIMATION
		this.updateAnimation(delta);
		
		// GRAVITY
		this.setY(this.getY() + this.velocityY);
		this.setPosition(this.getBody().x, this.getBody().y);
		
	}

	public Hitbox isCollidingHorizontal(Rectangle r) {
		return this.collideManager.isCollidingHorizontal(r);
	}

	public Hitbox isCollidingVertical(Rectangle r) {
		return this.collideManager.isCollidingVertical(r, this.isMovingUp());
	}
	
	public void updateColliding(ArrayList<Solid> solids, boolean canClimbDown, boolean canClimbUp) {
		for (Solid s : solids) {

			switch (this.isCollidingHorizontal(s.getBody())) {
			case CENTER:
				break;
			case LEFT:
				this.stopH(s.getX() + s.getWidth() + 1);
				break;
			case RIGHT:
				this.stopH(s.getX() - this.getWidth() - 1);
				break;
			default:
				break;
			}

			switch (this.isCollidingVertical(s.getBody())) {
			case CENTER:
				break;
			case BOTTOM:
				if (!canClimbDown) {
					this.resetJump();
					this.stopV(s.getY() + s.getHeight());
				}
				break;
			case TOP:
				if (!canClimbUp) {
					this.stopV(s.getY() - this.getHeight());
				}
				break;
			default:
				break;
			}
		}
	}
	
	public void draw(ShapeRenderer sr) {
		super.draw(sr);
		this.collideManager.draw(sr);
		this.waterAbility.draw(sr);
	}
	
	public void updateAnimation(float delta){
		this.sprite.update(delta);
	}
	
	private boolean isOnWater(ArrayList<WaterArea> water) {
		for (WaterArea w : water) {
			if (this.getBody().overlaps(w.getBody())){
				return true;
			}

		}
		return false;
	}
	
	private void setState(int i) {
		if (!this.state.equals(Hero.HERO_STATES[i])){
			this.state = Hero.HERO_STATES[i];
			this.sprite.reset();
		}
	}

	public boolean isMovingUp() {
		return (this.velocityY > 0); 
	}

	public void stopV(float height) {
		this.velocityY = 0;
		this.setPosition(this.getBody().x, height);
	}
	
	public void stopH(float width) {
		this.setPosition(width, this.getBody().y);
	}

	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.collideManager.setPosition(x, y);
		this.waterAbility.setPosition(x, y);
	}

	public void moveRight(float delta) {
		this.setX(this.getX() + 250 * delta);
		this.setState(2);
	}

	public void moveLeft(float delta) {
		this.setX(this.getX() - 250 * delta);
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
		batch.draw(this.sprite.getCurrentAnimation(this.state), this.getBody().x, this.getBody().y);
	}

	@Override
	public void dispose() {
		this.sprite.dispose();
	}

}
