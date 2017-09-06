package com.elemensia.api.gameobjects;

import com.elemensia.api.Animation;
import com.elemensia.api.State;

public abstract class Creature extends LivingThing{

	public Creature(int x, int y, int width, int height, int health, Animation animations) {
		super(x, y, width, height, health, animations);
	}

	public void eat(){
		this.animations.setAnimation(State.EATING, false);
	}
	public abstract void takeDecision(float delta);

}
