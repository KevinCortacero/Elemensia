package com.elemens;

public class Twarzian extends Creature {

	public static final String[] STATES = { "IDLE_RIGHT", "IDLE_LEFT", "WALK_RIGHT", "WALK_LEFT", "JUMP_RIGHT",
			"JUMP_LEFT", "SWIM_RIGHT", "SWIM_LEFT", "CLIMB" };

	private static final int HEALTH_POINT = 100;
	private static final int WIDTH = 256;
	private static final int HEIGHT = 128;

	public static final String SPRITE_PATH = "wolf3.png";

	private static final SpriteAnimation ANIMATION = new SpriteAnimation(WIDTH, HEIGHT, STATES, SPRITE_PATH);

	public Twarzian(int x, int y) {
		super(x, y, WIDTH, HEIGHT, ANIMATION, HEALTH_POINT);
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
