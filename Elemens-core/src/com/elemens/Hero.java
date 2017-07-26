package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero extends LivingThing {

	private final static int WIDTH = 50;
	private final static int HEIGHT = 50;
	private final static int MAX_HEALTH_POINT = 100;
	
	boolean canClimbUp;
	boolean canClimbDown;
	
	private SplineAnimations animations;
	
	public Hero(int x, int y) {               
		super(x, y, WIDTH, HEIGHT, MAX_HEALTH_POINT);
		this.canClimbUp = false;
		this.canClimbDown = false;
		this.animations = new SplineAnimations("spineboy-pma.atlas", "spineboy.json");
	}

	
	@Override
	public void draw(SpriteBatch batch, float delta) {
		this.animations.draw(batch);
	}
	
	private boolean isClimbingUp(ArrayList<Ladder> ladders){
		if (!Gdx.input.isKeyPressed(Input.Keys.Z))
			return false;
		for (Ladder l : ladders) {
			if (this.collideManager.getCenterBox().overlaps(l.climbZone)) {
				return true;
			}
		}
		return false;
	}

	private boolean isClimbingDown(ArrayList<Ladder> ladders){
		if (!Gdx.input.isKeyPressed(Input.Keys.S))
			return false;
		for (Ladder l : ladders) {
			if (this.collideManager.getCenterBox().overlaps(l.climbZoneDown)) {
				return true;
			}
		}
		return false;
	}
	
	public void update(float delta, Vector2 gravity, ArrayList<Ladder> ladders){
		super.update(delta, gravity, canClimbDown);
		
		// CLIMB
		this.canClimbDown = this.isClimbingDown(ladders);
		this.canClimbUp = this.isClimbingUp(ladders);
		
		this.animations.update(this.getX(), this.getY(), delta, this.direction);
	}

	public void updateInput() {
		if (this.canClimbUp) {
			this.climbUp();
		}
		if (this.canClimbDown) {
			this.climbDown();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			this.moveRight(Gdx.graphics.getDeltaTime());
			if (this.velocityY == 0)
				this.animations.setAnimation(State.WALKING, 0, true); // trackIndex, name, loop
			
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			this.moveLeft(Gdx.graphics.getDeltaTime());
			if (this.velocityY == 0)
				this.animations.setAnimation(State.WALKING, 0, true); // trackIndex, name, loop
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			this.jump();
			this.animations.setAnimation(State.JUMPING, 0, false); // trackIndex, name, loop
		}
	}

	public float getCenterX() {
		return this.collideManager.getCenterX();
	}

	public float getCenterY() {
		return this.collideManager.getCenterY();
	}
	
	@Override
	public void applyHorizontalCollidingEffect(CollideBox collider, Hitbox hitbox){
		switch (hitbox) {
		case CENTER:
			break;
		case LEFT:
			this.stopH(collider.getX() + collider.getWidth() + 1);
			break;
		case RIGHT:
			this.stopH(collider.getX() - this.getWidth() - 1);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void applyVerticalCollidingEffect(CollideBox collider, Hitbox hitbox){
		switch (hitbox) {
		case CENTER:
			break;
		case BOTTOM:
			if (!this.canClimbDown) {
				this.resetJump();
				this.stopV(collider.getY() + collider.getHeight());
			}
			break;
		case TOP:
			if (!this.canClimbUp && this.isMovingUp()) {
				this.stopV(collider.getY() - this.getHeight());
			}
			break;
		default:
			break;
		}
	}


}
