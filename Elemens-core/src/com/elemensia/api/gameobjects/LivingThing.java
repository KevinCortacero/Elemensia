package com.elemensia.api.gameobjects;

import com.elemensia.api.Animation;
import com.elemensia.api.Life;

public abstract class LivingThing extends DynamicGameObject{

	private Life life;

	public LivingThing(int x, int y, int width, int height, int health, Animation animations) {
		super(x, y, width, height, animations);
		this.life = new Life(health);
	}
	
	public boolean isAlive() {
		return true;
	}
}
