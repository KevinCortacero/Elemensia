package com.elemensia.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.elemensia.api.SplineAnimations;
import com.elemensia.api.SubState;
import com.elemensia.api.gameobjects.Decision;
import com.elemensia.api.gameobjects.LivingThing;
import com.elemensia.api.physics.CollideBox;
import com.elemensia.api.physics.Hitbox;

public class Hero extends LivingThing {

	private final static int WIDTH = 80;
	private final static int HEIGHT = 200;
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
	public void update(float gravity, float delta){
		super.update(gravity, delta);
		/*
		// CLIMB
		World.updateClimbing(this);

		World.updateColliding(this);
		 */

	}

	@Override
	public void applyGravity(float gravity, float delta){
		if (!this.waterAbility.isUnderWater){
			this.velocity.y += (gravity*delta);
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
			this.resetJump();
			this.stopV(collider.getY() + collider.getHeight());
			this.setStateValue("ENVIRONMENT", SubState.GROUND);
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

	@Override
	public void updateDecision() {
		this.setDecisionValue(Decision.RIGHT, Input.Keys.D);
		this.setDecisionValue(Decision.LEFT, Input.Keys.Q);
		this.setDecisionValue(Decision.TOP, Input.Keys.Z);
		this.setDecisionValue(Decision.DOWN, Input.Keys.S);
		this.setDecisionValue(Decision.JUMP, Input.Keys.SPACE);
	}

}
