package com.elemensia.api;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.elemensia.game.World;

public abstract class DynamicGameObject extends GameObject{

	protected State state;
	protected Direction direction;
	protected CollideManager collideManager;
	protected WaterAbility waterAbility;
	protected Vector2 velocity;
	private int jumpCount;
	protected Animation animations;
	
	public DynamicGameObject(int x, int y, int width, int height, Animation animations) {
		super(x, y, width, height);
		this.waterAbility = new WaterAbility(x, y, width, height);
		this.collideManager = new CollideManager(x, y, width, height);
		this.animations = animations;
		this.state = State.IDLE;
		this.direction = Direction.LEFT;
		this.velocity = new Vector2();
		this.jumpCount = 1;
	}
	
	public float getCenterX() {
		return this.collideManager.getCenterX();
	}

	public float getCenterY() {
		return this.collideManager.getCenterY();
	}
	
	public void update(Vector2 gravity, float delta){
		this.waterAbility.isOnWater = World.isOnWater(this);
		this.waterAbility.isUnderWater = World.isUnderWater(this);
		
		// normal gravity
		this.applyGravity(gravity, delta);

		// under water
		if (this.waterAbility.isUnderWater){
			this.velocity.y *= 0.95;
			if (this.velocity.y < 0.5 && this.velocity.y > -0.5){
				this.resetJump();
			}
		}

		// on water
		if (this.waterAbility.isOnWater){
			this.velocity.y -= (gravity.y*delta*0.75);			
		}
		//  ANIMATION
		this.animations.update(this.getCenterX(), this.getY(), delta, this.direction);

		// GRAVITY
		this.setY(this.getY() + this.velocity.y);
		this.setPosition(this.getBody().x, this.getBody().y);

	}

	public abstract void applyGravity(Vector2 gravity, float delta);

	public abstract void applyHorizontalCollidingEffect(CollideBox collider, Hitbox hitbox);
	
	public abstract void applyVerticalCollidingEffect(CollideBox collider, Hitbox hitbox);

	public void draw(ShapeRenderer sr) {
		super.draw(sr);
		this.collideManager.draw(sr);
		this.waterAbility.draw(sr);
		this.animations.draw(sr);
	}
	
	public void draw(SpriteBatch batch, float delta) {
		this.animations.draw(batch);
	}

	public boolean isMovingUp() {
		return (this.velocity.y > 0); 
	}

	public void stopV(float height) {
		this.velocity.y = 0;
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
		this.direction = Direction.RIGHT;
	}

	public void moveLeft(float delta) {
		this.setX(this.getX() - 150 * delta);
		this.direction = Direction.LEFT;
	}

	public void climbUp() {
		this.velocity.y = 4;
	}

	public void climbDown() {
		this.velocity.y = -4;
	}

	public void jump() {
		if (this.jumpCount > 0) {
			this.velocity.y = 8;
			this.jumpCount--;
			this.animations.setAnimation(State.JUMPING, false);
		}
	}

	public void resetJump() {
		this.jumpCount = 1;
	}
	
	public CollideBox getWaterBox() {
		return this.waterAbility.waterBox;
	}

}
