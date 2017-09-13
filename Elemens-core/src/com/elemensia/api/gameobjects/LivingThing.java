package com.elemensia.api.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.elemensia.api.Animation;
import com.elemensia.api.GlobalState;
import com.elemensia.api.Organism;
import com.elemensia.api.SubState;
import com.elemensia.api.StatusManager;
import com.elemensia.game.World;

public abstract class LivingThing extends DynamicGameObject {

	// private Life life;
	private Thread alive;
	private DecisionManager decisionManager;
	private StatusManager statusManager;
	private Organism organism;

	private static BitmapFont font = new BitmapFont();
	static {
		font.setColor(Color.BLACK);
	}

	public LivingThing(int x, int y, int width, int height, int health, Animation animations) {
		super(x, y, width, height, animations);
		// this.life = new Life(health);
		// For now, temporary
		this.organism = new Organism(health, 0.001f, 100, 0.001f, 100);
		this.decisionManager = new DecisionManager();
		this.statusManager = new StatusManager();
		this.alive = new Thread(new Runnable() {

			@Override
			public void run() {

				float delta = 1 / 60.0f;
				int delay = (int) (delta * 1000);
				do {
					LivingThing.this.update(-9.8f, delta);
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (LivingThing.this.isAlive());

			}

		});
	}

	@Override
	public void update(float gravity, float delta) {
		super.update(gravity, delta);
		World.updateColliding(this);
		if (this.waterAbility.isOnWater){
			this.setStateValue("ENVIRONMENT", SubState.WATER);
		}
		this.updateDecision();
	
		// STATE
		this.updateState();
		// ANIMATION
		this.animations.update(this.getCenterX(), this.getY(), delta, this.statusManager.getState("DIRECTIONH"));
		// ACTION
		this.act(delta);
		// ORGANISM
		this.organism.live();
	}

	public void updateState() {
		this.statusManager.updateStatus(this);
	}
	
	public void setStateValue(String string, SubState ground) {
		this.statusManager.setState(string, ground);
	}

	private void act(float delta) {
		if (this.statusManager.getState("DIRECTIONH") != SubState.NONE) {
			this.animations.setAnimation(GlobalState.WALK, true);
			if (statusManager.getState("DIRECTIONH") == SubState.RIGHT) {
				this.moveRight(delta);
			} else if (statusManager.getState("DIRECTIONH") == SubState.LEFT) {
				this.moveLeft(delta);
			}
		} 
		
		else if (this.statusManager.getState("DIRECTIONH") == SubState.NONE) {
			this.animations.setAnimation(GlobalState.IDLE, true);
		} 
		if (this.statusManager.getState("MOVEMENT") == SubState.JUMP) {
			this.jump();
		}
	}

	public abstract void updateDecision();

	public boolean isAlive() {
		return true;
	}

	public boolean getDecisionValue(Decision decisionName) {
		return this.decisionManager.getDecisionValue(decisionName);
	}

	public void setDecisionValue(Decision decisionName, boolean value) {
		this.decisionManager.setDecisionValue(decisionName, value);
	}

	public void setDecisionValue(Decision decisionName, int keyPressed) {
		this.decisionManager.setDecisionValue(decisionName, keyPressed);
	}

	public SubState getState(String stateName) {
		return this.statusManager.getState(stateName);
	}

	public void live() {
		this.alive.start();
	}
	
	@Override
	public void draw(SpriteBatch batch, float delta) {
		super.draw(batch, delta);
		this.font.draw(batch, this.statusManager.getValue(), this.getX() - 50, this.getY() + this.getHeight() + 20);
	}

}
