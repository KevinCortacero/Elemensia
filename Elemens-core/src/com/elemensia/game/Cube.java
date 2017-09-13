package com.elemensia.game;

import com.elemensia.api.GlobalState;
import com.elemensia.api.SpriteAnimation;
import com.elemensia.api.SubState;
import com.elemensia.api.gameobjects.Creature;
import com.elemensia.api.gameobjects.Decision;
import com.elemensia.api.physics.CollideBox;
import com.elemensia.api.physics.Hitbox;

public class Cube extends Creature{

	public static GlobalState STATES[] = {GlobalState.WALK, GlobalState.IDLE, GlobalState.EAT, GlobalState.JUMP};
	public static int WIDTH = 64;
	public static int HEIGHT = 64;

	public Cube(int x, int y) {
		super(x, y, WIDTH, HEIGHT, 100, new SpriteAnimation(WIDTH, HEIGHT, STATES, "living_things/creatures/cube/cube.png"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateDecision() {
		this.setDecisionValue(Decision.JUMP, false);
		if (Math.random() < 0.005){
			if (Math.random() < 0.5){
				if (this.getState("DIRECTIONH") == SubState.RIGHT || this.getState("DIRECTIONH") == SubState.NONE){
					this.setDecisionValue(Decision.RIGHT, false);
					this.setDecisionValue(Decision.LEFT, true);
				}
				else if (this.getState("DIRECTIONH") == SubState.LEFT){
					this.setDecisionValue(Decision.RIGHT, true);
					this.setDecisionValue(Decision.LEFT, false);
				}
		
				if (Math.random() < 0.5) {
					this.setDecisionValue(Decision.JUMP, true);
				}
			}
			else if (Math.random() < 0.5){
				this.setDecisionValue(Decision.RIGHT, false);
				this.setDecisionValue(Decision.LEFT, false);
			}
			
		}
		
	}

	@Override
	public void applyGravity(float gravity, float delta) {
		if (!this.waterAbility.isUnderWater){
			this.velocity.y += (gravity*delta);
		}
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
			this.setStateValue("ENVIRONMENT", SubState.GROUND);

			break;
		case TOP:
			this.stopV(collider.getY() - this.getHeight());

			break;
		default:
			break;
		}
	}

}
