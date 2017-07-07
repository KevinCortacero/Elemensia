package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public abstract class Creature extends LivingThing{

	public Creature(int x, int y, int width, int height, SpriteAnimation sprite, int health) {
		super(x, y, width, height, sprite, health);
		// TODO Auto-generated constructor stub
	}

	public abstract void takeDecision(float delta);
	
	@Override
	public void update(float delta, Vector2 gravity, boolean canClimbUp) {
		super.update(delta, gravity, canClimbUp);
		//this.velocityY += (gravity.y*delta*3);
	}

}
