package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Hero extends LivingThing {

	public static final String[] HERO_STATES = {"IDLE_RIGHT", "IDLE_LEFT", "WALK_RIGHT", "WALK_LEFT", 
			"JUMP_RIGHT", "JUMP_LEFT", "SWIM_RIGHT", "SWIM_LEFT", "CLIMB"};

	public static final String HERO_SPRITE_PATH = "blizz.png";
	private static SpriteAnimation sprite = new SpriteAnimation(50, 50, HERO_STATES, HERO_SPRITE_PATH);
	boolean canClimbUp;
	boolean canClimbDown;

	public Hero(int x, int y) {               
		super(x, y, 50, 50, sprite, 100);
		this.canClimbUp = false;
		this.canClimbDown = false;
	}

	public void update(float delta, Vector2 gravity, ArrayList<WaterArea> water, ArrayList<Ladder> ladders){
		super.update(delta, gravity, canClimbDown, water);
		
		// CLIMB
		this.canClimbDown = this.isClimbingDown(ladders);
		this.canClimbUp = this.isClimbingUp(ladders);
	}

	public void updateInput() {
		if (this.canClimbUp) {
			this.climbUp();
		}
		if (this.canClimbDown) {
			this.climbDown();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			this.moveRight(Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			this.moveLeft(Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			this.jump();
		}
	}

	private boolean isClimbingUp(ArrayList<Ladder> ladders){
		if (!Gdx.input.isKeyPressed(Input.Keys.Z))
			return false;
		for (Ladder l : ladders) {
			if (this.collideManager.getCenterBox().overlaps(l.climbZone)) {
				return true;
			}
		}
		return false;
	}

	private boolean isClimbingDown(ArrayList<Ladder> ladders){
		if (!Gdx.input.isKeyPressed(Input.Keys.S))
			return false;
		for (Ladder l : ladders) {
			if (this.collideManager.getCenterBox().overlaps(l.climbZoneDown)) {
				return true;
			}
		}
		return false;
	}

	public float getCenterX() {
		return this.collideManager.getCenterX();
	}
	
	public float getCenterY() {
		return this.collideManager.getCenterY();
	}

	
}
