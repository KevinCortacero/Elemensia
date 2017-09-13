package com.elemensia.api.gameobjects;

import com.elemensia.api.GlobalState;
import com.elemensia.api.SpriteAnimation;
import com.elemensia.api.physics.CollideBox;
import com.elemensia.api.physics.Hitbox;

public class Food extends DynamicGameObject {
	
	public static GlobalState[] STATES = {GlobalState.IDLE};

	public Food(int x, int y) {
		super(x, y, 64, 64, new SpriteAnimation(64, 64, STATES, "apple.png"));
		// TODO Auto-generated constructor stub
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
