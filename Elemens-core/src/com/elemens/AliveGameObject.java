package com.elemens;

public abstract class AliveGameObject extends DynamicGameObject{

	private Life life;
	
	public AliveGameObject(int x, int y, int width, int height, String[] states, String spritePath, int health) {
		super(x, y, width, height, states, spritePath);
		this.life = new Life(health);
	}

}
