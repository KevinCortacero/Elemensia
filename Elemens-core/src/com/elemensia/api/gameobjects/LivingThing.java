package com.elemensia.api.gameobjects;

import com.elemensia.api.Animation;
import com.elemensia.api.Life;
import com.elemensia.api.StateManager;

public abstract class LivingThing extends DynamicGameObject{

	private Life life;
	
	private InputManager inputs;

	public LivingThing(int x, int y, int width, int height, int health, Animation animations) {
		super(x, y, width, height, animations);
		this.life = new Life(health);
		this.inputs = new InputManager();
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
}
