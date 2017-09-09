package com.elemensia.game;

import com.elemensia.api.SpriteAnimation;
import com.elemensia.api.State;
import com.elemensia.api.Status;
import com.elemensia.api.gameobjects.Creature;
import com.elemensia.api.physics.CollideBox;
import com.elemensia.api.physics.Hitbox;

public class Cube extends Creature{

	public static State STATES[] = {State.WALK, State.IDLE, State.EAT, State.JUMP};
	public static int WIDTH = 64;
	public static int HEIGHT = 64;

	public Cube(int x, int y) {
		super(x, y, WIDTH, HEIGHT, 100, new SpriteAnimation(WIDTH, HEIGHT, STATES, "living_things/creatures/cube/cube.png"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateDecision() {
		// TODO Auto-generated method stub
		this.setDecisionValue("RIGHT", true);
	}

	@Override
	public void applyGravity(float gravity, float delta) {
		this.velocity.y += (gravity * delta);
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
