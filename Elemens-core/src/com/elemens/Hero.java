package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Hero extends LivingThing {

	private final static int WIDTH = 50;
	private final static int HEIGHT = 50;
	private final static int MAX_HEALTH_POINT = 100;

	public boolean canClimbUp;
	public boolean canClimbDown;

	private static SplineAnimations ANIMATIONS = new SplineAnimations("living_things/hero/spineboy-pma.atlas", "living_things/hero/spineboy.json");

	public Hero(int x, int y) {               
		super(x, y, WIDTH, HEIGHT, MAX_HEALTH_POINT, ANIMATIONS);
		this.canClimbUp = false;
		this.canClimbDown = false;
	}

	@Override
	public void update(Vector2 gravity, float delta){
		super.update(gravity, delta);

		// CLIMB
		World.updateClimbing(this);

	}
	
	@Override
	public void applyGravity(Vector2 gravity, float delta){
		if (!(canClimbUp && Gdx.input.isKeyPressed(Input.Keys.Z)) && !this.waterAbility.isUnderWater){
			this.velocity.y += (gravity.y*delta);
		}
	}

	public void updateInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.Z) && this.canClimbUp) {
			this.climbUp();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S) && this.canClimbDown) {
			this.climbDown();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			this.moveRight(Gdx.graphics.getDeltaTime());
			if (this.velocity.y == 0)
				this.animations.setAnimation(State.WALKING, 0, true); // trackIndex, name, loop

		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			this.moveLeft(Gdx.graphics.getDeltaTime());
			if (this.velocity.y == 0)
				this.animations.setAnimation(State.WALKING, 0, true); // trackIndex, name, loop
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			this.jump();
			this.animations.setAnimation(State.JUMPING, 0, false); // trackIndex, name, loop
		}
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
			if (! (this.canClimbDown && Gdx.input.isKeyPressed(Input.Keys.S))) {
				this.resetJump();
				this.stopV(collider.getY() + collider.getHeight());
			}
			break;
		case TOP:
			if (! (this.canClimbUp && Gdx.input.isKeyPressed(Input.Keys.Z)) && this.isMovingUp()) {
				this.stopV(collider.getY() - this.getHeight());
			}
			break;
		default:
			break;
		}
	}


}
