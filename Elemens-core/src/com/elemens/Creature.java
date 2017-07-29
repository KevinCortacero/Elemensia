package com.elemens;

import com.badlogic.gdx.math.Vector2;

public abstract class Creature extends LivingThing{

	public Creature(int x, int y, int width, int height, int health, SplineAnimations animations) {
		super(x, y, width, height, health, animations);
	}

	
	@Override
	public void update(float delta, Vector2 gravity, boolean canClimbUp) {
		super.update(delta, gravity, canClimbUp);
	}
	
	public abstract void takeDecision(float delta);

}
