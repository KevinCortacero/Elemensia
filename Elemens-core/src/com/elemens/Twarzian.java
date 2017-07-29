package com.elemens;

public class Twarzian extends Creature {

	private static final int HEALTH_POINT = 100;
	private static final int WIDTH = 256;
	private static final int HEIGHT = 128;

	private static final SplineAnimations ANIMATIONS = new SplineAnimations("living_things/creatures/wolf/alien.atlas", "living_things/creatures/wolf/alien.json");

	public Twarzian(int x, int y) {
		super(x, y, WIDTH, HEIGHT, HEALTH_POINT, ANIMATIONS);
	}

	@Override
	public void takeDecision(float delta) {
		/*
		 * if (Math.random() < 0.05) this.jump(); if (Math.random() < 0.05){
		 * if(false) this.moveRight(delta); else this.moveLeft(delta); }
		 */
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
}
