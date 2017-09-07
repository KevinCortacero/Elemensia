package com.elemensia.game;

import com.elemensia.api.SpriteAnimation;
import com.elemensia.api.State;
import com.elemensia.api.gameobjects.Creature;
import com.elemensia.api.physics.CollideBox;
import com.elemensia.api.physics.Hitbox;

public class Cube extends Creature{

	public static State STATES[] = {State.WALKING, State.IDLE, State.EATING, State.JUMPING};
	public static int WIDTH = 64;
	public static int HEIGHT = 64;

	public Cube(int x, int y) {
		super(x, y, WIDTH, HEIGHT, 100, new SpriteAnimation(WIDTH, HEIGHT, STATES, "living_things/creatures/cube/cube.png"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeDecision(float delta) {
		if (Math.random() < 0.005) 
			this.jump(); 
		else if(Math.random() < 0.005)
			this.eat();
		else 
			this.moveRight(delta); 
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
