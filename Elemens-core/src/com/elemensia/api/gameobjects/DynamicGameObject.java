package com.elemensia.api.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.elemensia.api.Animation;
import com.elemensia.api.State;
import com.elemensia.api.physics.CollideBox;
import com.elemensia.api.physics.CollideManager;
import com.elemensia.api.physics.Hitbox;
import com.elemensia.api.physics.WaterAbility;
import com.elemensia.game.World;

public abstract class DynamicGameObject extends InteractiveGameObject{

	private CollideManager collideManager;
	protected WaterAbility waterAbility;
	public Vector2 velocity;
	private int jumpCount;
	protected Animation animations;
	
	public DynamicGameObject(int x, int y, int width, int height, Animation animations) {
		super(x, y, width, height);
		this.waterAbility = new WaterAbility(x, y, width, height);
		this.collideManager = new CollideManager(x, y, width, height);
		this.animations = animations;
		this.velocity = new Vector2();
		this.jumpCount = 3;
	}
	
	public boolean overlaps(Rectangle rectangle) {
		return this.collideManager.getCenterBox().overlaps(rectangle);
	}
	
	public float getCenterX() {
		return this.collideManager.getCenterX();
	}

	public float getCenterY() {
		return this.collideManager.getCenterY();
	}
	
	public void update(float gravity, float delta){
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
			this.velocity.y -= (gravity*delta*0.75);			
		}
		

		// GRAVITY
		this.setY(this.getY() + this.velocity.y);
		this.setPosition(this.getBody().x, this.getBody().y);
		
		if (this.getCenterY() < -500)
			this.setPosition(200, 300);

	}

	public abstract void applyGravity(float gravity, float delta);

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
		this.setX(this.getX() + 200 * delta);
	}

	public void moveLeft(float delta) {
		this.setX(this.getX() - 200 * delta);
	}

	public void climbUp() {
		this.velocity.y = 5;
	}

	public void climbDown() {
		this.velocity.y = -5;
	}

	public void jump() {
		if (this.jumpCount > 0) {
			this.velocity.y = 5;
			this.jumpCount--;
		}
	}

	public void resetJump() {
		this.jumpCount = 3;
	}
	
	public CollideBox getWaterBox() {
		return this.waterAbility.waterBox;
	}

	public Hitbox isCollapsingHorizontal(CollideBox box) {
		return this.collideManager.isCollidingHorizontal(box);
	}
	
	public Hitbox isCollapsingVertical(CollideBox box) {
		return this.collideManager.isCollidingVertical(box);
	}

}
