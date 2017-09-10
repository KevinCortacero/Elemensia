package com.elemensia.game;

import com.elemensia.api.SpriteAnimation;
import com.elemensia.api.State;
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
		if (Math.random() < 0.005){
			if (Math.random() < 0.5){
				if (this.getState("DIRECTIONH") == State.RIGHT){
					this.setDecisionValue("RIGHT", false);
					this.setDecisionValue("LEFT", true);
					System.out.println("LEFT !");
				}
				else if (this.getState("DIRECTIONH") == State.LEFT){
					this.setDecisionValue("RIGHT", true);
					this.setDecisionValue("LEFT", false);
					System.out.println("RIGHT !");
				}
			}
			else {
				this.setDecisionValue("RIGHT", false);
				this.setDecisionValue("LEFT", false);
				System.out.println("STOP !");
			}
			
		}
		
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
