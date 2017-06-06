package com.elemens;

public abstract class Creature extends LivingThing{

	public Creature(int x, int y, int width, int height, SpriteAnimation sprite, int health) {
		super(x, y, width, height, sprite, health);
		// TODO Auto-generated constructor stub
	}

	public abstract void takeDecision(float delta);

}
