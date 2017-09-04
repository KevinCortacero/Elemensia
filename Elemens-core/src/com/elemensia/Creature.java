package com.elemensia;

import com.badlogic.gdx.math.Vector2;

public abstract class Creature extends LivingThing{

	public Creature(int x, int y, int width, int height, int health, Animation animations) {
		super(x, y, width, height, health, animations);
	}

	
	@Override
	public void update(Vector2 gravity, float delta) {
		super.update(gravity, delta);
	}
	
	public void eat(){
		this.animations.setAnimation(State.EATING, false);
	}
	public abstract void takeDecision(float delta);

}
