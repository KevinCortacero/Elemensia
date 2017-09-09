package com.elemensia.api.gameobjects;

import com.elemensia.api.Animation;
import com.elemensia.api.Life;
import com.elemensia.api.StatusManager;
import com.elemensia.game.World;

public abstract class LivingThing extends DynamicGameObject{

	private Life life;
	private Thread alive;
	private DecisionManager decisionManager;
	private StatusManager statusManager;

	public LivingThing(int x, int y, int width, int height, int health, Animation animations) {
		super(x, y, width, height, animations);
		this.life = new Life(health);
		this.decisionManager = new DecisionManager();
		this.statusManager = new StatusManager();
		this.alive = new Thread(new Runnable() {

			@Override
			public void run() {
				
				float delta = 1 / 60.0f;
				int delay = (int) (delta * 1000);
				System.out.println(delta + " " + delay);
				do {
					LivingThing.this.update(-9.8f, delta);
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}while(LivingThing.this.isAlive());

			}

		});
	}
	
	@Override
	public void update(float gravity, float delta) {
		super.update(gravity, delta);
		World.updateColliding(this);
		this.updateDecision();
		this.updateState();
		this.act(delta);
	}
	
	public void updateState(){
		this.statusManager.updateStatus(this);
	}
	
	private void act(float delta) {
		if (this.getDecisionValue("RIGHT"))
			this.moveRight(delta);
		
	}

	public abstract void updateDecision();
	
	public boolean isAlive() {
		return true;
	}

	public boolean getDecisionValue(String inputName) {
		return this.decisionManager.getDecisionValue(inputName);
	}
	
	public void setDecisionValue(String inputName, boolean value){
		this.decisionManager.setDecisionValue(inputName, value);
	}
	
	public void setDecisionValue(String inputName, int keyPressed){
		this.decisionManager.setDecisionValue(inputName, keyPressed);
	}
	/*
	public void updateInputs() {
		this.decisionManager.updateInputs();
		StateManager.updateState(this);
	}
	*/
	
	public void live(){
		this.alive.start();
	}
}
