package com.elemensia.api.gameobjects;

import com.elemensia.api.Animation;
import com.elemensia.api.Life;
import com.elemensia.api.StateManager;

public abstract class LivingThing extends DynamicGameObject{

	private Life life;
	private Thread alive;
	private InputManager inputs;

	public LivingThing(int x, int y, int width, int height, int health, Animation animations) {
		super(x, y, width, height, animations);
		this.life = new Life(health);
		this.inputs = new InputManager();
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
	
	public boolean isAlive() {
		return true;
	}

	public boolean getInputValue(String inputName) {
		return this.inputs.getInputValue(inputName);
	}
	
	public void updateInputs() {
		this.inputs.updateInputs();
		StateManager.updateState(this);
	}
	
	public void live(){
		this.alive.start();
	}
}
