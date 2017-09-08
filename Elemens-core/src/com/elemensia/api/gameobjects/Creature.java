package com.elemensia.api.gameobjects;

import com.elemensia.api.Animation;
import com.elemensia.api.State;
import com.elemensia.game.World;

public abstract class Creature extends LivingThing{

	public Creature(int x, int y, int width, int height, int health, Animation animations) {
		super(x, y, width, height, health, animations);
	}
	
	public void eat(){
		this.animations.setAnimation(State.EATING, false);
	}

	@Override
	public void update(float gravity, float delta) {
		super.update(gravity, delta);
		World.updateColliding(this);
		this.takeDecision(delta);
	}
	public abstract void takeDecision(float delta);

}
