package com.elemensia.game;

import com.elemensia.api.SplineAnimations;
import com.elemensia.api.gameobjects.Creature;
import com.elemensia.api.physics.CollideBox;
import com.elemensia.api.physics.Hitbox;

public class Twarzian extends Creature {

	private static final int HEALTH_POINT = 100;
	private static final int WIDTH = 80;
	private static final int HEIGHT = 100;

	private static final SplineAnimations ANIMATIONS = new SplineAnimations("living_things/creatures/wolf/alien.atlas", "living_things/creatures/wolf/alien.json");

	public Twarzian(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HEALTH_POINT, ANIMATIONS);
	}

	@Override
	public void applyHorizontalCollidingEffect(CollideBox collider, Hitbox hitbox) {
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
	public void applyVerticalCollidingEffect(CollideBox collider, Hitbox hitbox) {
		switch (hitbox) {
		case CENTER:
			break;
		case BOTTOM:
			this.resetJump();
			this.stopV(collider.getY() + collider.getHeight());

			break;
		case TOP:
			this.stopV(collider.getY() - this.getHeight());

			break;
		default:
			break;
		}
	}

	@Override
	public void applyGravity(float gravity, float delta) {
		this.velocity.y += (gravity * delta);
	}

	@Override
	public void updateDecision() {
		// TODO Auto-generated method stub
		
	}
}
