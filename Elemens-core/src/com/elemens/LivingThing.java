package com.elemens;

public abstract class LivingThing extends DynamicGameObject{

	private Life life;

	public LivingThing(int x, int y, int width, int height, int health, SplineAnimations animations) {
		super(x, y, width, height, animations);
		this.life = new Life(health);
	}
}
