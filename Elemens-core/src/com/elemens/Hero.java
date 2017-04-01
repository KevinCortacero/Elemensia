package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Hero extends AliveGameObject {

	public static final String[] STATES = {"IDLE_RIGHT", "IDLE_LEFT", "WALK_RIGHT", "WALK_LEFT", 
			"JUMP_RIGHT", "JUMP_LEFT", "SWIM_RIGHT", "SWIM_LEFT", "CLIMB"};

	boolean canClimbUp;
	boolean canClimbDown;

	public Hero(int x, int y, int width, int height) {               
		super(x, y, width, height, STATES, "Blizz.png", 100);
		this.canClimbUp = false;
		this.canClimbDown = false;
	}

	public void update(float delta, Vector2 gravity, ArrayList<Solid> water, ArrayList<Ladder> ladders){
		super.update(delta, gravity, canClimbDown, water);
		
		
		
		// CLIMB
		this.canClimbDown = this.isClimbingDown(ladders);
		this.canClimbUp = this.isClimbingUp(ladders);
	}

	public Hitbox isCollidingHorizontal(Rectangle r) {
		return this.collideManager.isCollidingHorizontal(r);
	}

	public Hitbox isCollidingVertical(Rectangle r) {
		return this.collideManager.isCollidingVertical(r, this.isMovingUp());
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
