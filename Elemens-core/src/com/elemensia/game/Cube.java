package com.elemensia.game;

import com.badlogic.gdx.math.Vector2;
import com.elemensia.api.CollideBox;
import com.elemensia.api.Creature;
import com.elemensia.api.Hitbox;
import com.elemensia.api.SpriteAnimation;
import com.elemensia.api.State;

public class Cube extends Creature{

	public static State STATES[] = {State.WALKING, State.IDLE, State.EATING, State.JUMPING};

	public Cube(int x, int y) {
		super(x, y, 64, 64, 100, new SpriteAnimation(64, 64, STATES, "living_things/creatures/cube/cube.png"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeDecision(float delta) {
		if (Math.random() < 0.0005) 
			this.jump(); 
		else 
			this.moveRight(delta); 
	}

	@Override
	public void applyGravity(Vector2 gravity, float delta) {
		this.velocity.y += (gravity.y * delta);
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
