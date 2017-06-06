package com.elemens;

public class Twarzian extends Creature{

	public static final String[] TWARZIAN_STATES = {"IDLE_RIGHT", "IDLE_LEFT", "WALK_RIGHT", "WALK_LEFT", 
			"JUMP_RIGHT", "JUMP_LEFT", "SWIM_RIGHT", "SWIM_LEFT", "CLIMB"};

	private static final int TWARZIAN_WIDTH = 128;
	private static final int TWARZIAN_HEIGHT = 128;
	
	public static final String TWARZIAN_SPRITE_PATH = "blizz128.png";
	
	public Twarzian(int x, int y) {
		super(x, y, 128, 128, new SpriteAnimation(TWARZIAN_WIDTH, TWARZIAN_HEIGHT, TWARZIAN_STATES, TWARZIAN_SPRITE_PATH), 100);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void takeDecision(float delta) {
		if (Math.random() < 0.05)
			this.jump();
		if (Math.random() < 0.05){
			if(false)
				this.moveRight(delta);
			else
				this.moveLeft(delta);
		}

	}
}
