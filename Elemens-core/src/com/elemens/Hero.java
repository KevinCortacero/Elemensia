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

	public void update(float delta, Vector2 gravity){
		super.update();
		
		if (!this.canClimbUp && !this.isUnderWater()){
			this.velocityY += (gravity.y*delta*2.5);
		}
		
		if (this.isUnderWater()){
			this.velocityY *= 0.95;
			if (this.velocityY < 0.5 && this.velocityY > -0.5){
				this.resetJump();
			}
		}

		if (this.isOnWater()){
			this.velocityY -= (gravity.y*delta*1.25);			
		}
		
		//  ANIMATION
		this.updateAnimation(delta);
		
		// GRAVITY
		this.body.y += this.velocityY;
		this.setPosition(body.x, body.y);
		
		// CLIMB
		this.canClimbDown = this.isClimbingDown();
		this.canClimbUp = this.isClimbingUp(null);
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

	public boolean isOnWater(Rectangle water) {
		return (this.body.overlaps(water));
	}

	public boolean isUnderWater(Rectangle water) {
		return (this.waterBox.overlaps(water));
	}

	private boolean isOnWater() {
		for (Solid w : this.water) {
			if (this.hero.isOnWater(w.body)){
				return true;
			}

		}
		return false;
	}

	private boolean isUnderWater() {
		for (Solid w : this.water) {
			if (this.hero.isUnderWater(w.body)){
				return true;
			}
		}
		return false;
	}

	private boolean isClimbingUp(ArrayList<Ladder> ladders){
		if (!Gdx.input.isKeyPressed(Input.Keys.Z))
			return false;
		for (Ladder l : ladders) {
			if (this.center.overlaps(l.climbZone)) {
				return true;
			}
		}
		return false;
	}

	private boolean isClimbingDown(){
		if (!Gdx.input.isKeyPressed(Input.Keys.S))
			return false;
		for (Ladder l : this.ladders) {
			if (this.hero.center.overlaps(l.climbZoneDown)) {
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
