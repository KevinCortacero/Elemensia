package com.elemens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class LivingThing extends DynamicGameObject{

	private Life life;

	public LivingThing(int x, int y, int width, int height, SpriteAnimation sprite, int health) {
		super(x, y, width, height, sprite);
		this.life = new Life(health);
	}
	
	public LivingThing(int x, int y, int width, int height, int health) {
		super(x, y, width, height);
		this.life = new Life(health);
	}

	private String getLife(){
		return this.life.toString();
	}
}
