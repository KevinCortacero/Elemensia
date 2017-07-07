package com.elemens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public abstract class DynamicGameObject extends GameObject implements Disposable {


	protected CollideManager collideManager;
	private WaterAbility waterAbility;
	protected float velocityY;
	private int jumpCount;
	private String state;
	private SpriteAnimation sprite;
	private float timer;

	public DynamicGameObject(int x, int y, int width, int height, SpriteAnimation sprite) {
		super(x, y, width, height);
		this.waterAbility = new WaterAbility(x, y, width, height);
		this.collideManager = new CollideManager(x, y, width, height);
		this.sprite = sprite;
		this.state = sprite.getDefaultState();
		this.setState(0);
		this.velocityY = 0;
		this.jumpCount = 1;
		this.timer = 0;
		
	}

	public void update(float delta, Vector2 gravity, boolean canClimbUp){
		this.waterAbility.isOnWater = World.isOnWater(this);
		this.waterAbility.isUnderWater = World.isUnderWater(this);
		// normal gravity
		if (!canClimbUp && !this.waterAbility.isUnderWater){
			this.velocityY += (gravity.y*delta*1.5);
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
			this.velocityY -= (gravity.y*delta*0.75);			
		}
		//  ANIMATION
		this.updateAnimation(delta);

		// GRAVITY
		this.setY(this.getY() + this.velocityY);
		this.setPosition(this.getBody().x, this.getBody().y);

	}

	public abstract void applyHorizontalCollidingEffect(CollideBox collider, Hitbox hitbox);
	
	public abstract void applyVerticalCollidingEffect(CollideBox collider, Hitbox hitbox);
	
	public void updateColliding() {
		World.updateHorizontalColliding(this);
		World.updateVerticalColliding(this);
	}

	public void draw(ShapeRenderer sr) {
		super.draw(sr);
		this.collideManager.draw(sr);
		this.waterAbility.draw(sr);
	}

	public void updateAnimation(float delta){
		this.timer += delta;
	}

	private void setState(int i) {
		if (!this.state.equals(Hero.HERO_STATES[i])){
			this.state = Hero.HERO_STATES[i];
			this.timer = 0;
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
		this.setX(this.getX() + 150 * delta);
		this.setState(2);
	}

	public void moveLeft(float delta) {
		this.setX(this.getX() - 150 * delta);
		this.setState(3);
	}

	public void climbUp() {
		this.velocityY = 4;
		this.setState(8);
	}

	public void climbDown() {
		this.velocityY = -4;
		this.setState(8);
	}

	public void jump() {
		if (this.jumpCount > 0) {
			this.velocityY = 8;
			this.jumpCount--;
			this.setState(4);
		}
	}

	public void resetJump() {
		this.jumpCount = 1;
	}

	public void draw(SpriteBatch batch, float delta) {
		batch.draw(this.sprite.getCurrentAnimation(this.state, this.timer), this.getX(), this.getY());
	}

	@Override
	public void dispose() {
		this.sprite.dispose();
	}

	public CollideBox getWaterBox() {
		return this.waterAbility.waterBox;
	}

}
