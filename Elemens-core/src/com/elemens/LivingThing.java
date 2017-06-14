package com.elemens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class LivingThing extends DynamicGameObject{

	private Life life;

	public LivingThing(int x, int y, int width, int height, SpriteAnimation sprite, int health) {
		super(x, y, width, height, sprite);
		this.life = new Life(health);
	}

	public void draw(SpriteBatch batch, float delta) {
		super.draw(batch, delta);
		//font.draw(batch, this.getLife(), this.getX(), this.getY() + this.getHeight() + 20);
	}

	private String getLife(){
		return this.life.toString();
	}

}
