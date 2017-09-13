package com.elemensia.api;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.elemensia.api.gameobjects.Creature;
import com.elemensia.api.gameobjects.DynamicGameObject;
import com.elemensia.api.gameobjects.Ladder;
import com.elemensia.api.gameobjects.LivingThing;
import com.elemensia.api.gameobjects.Solid;
import com.elemensia.api.physics.Hitbox;
import com.elemensia.game.Hero;
import com.elemensia.game.MainWorldMap;

public class Ecosystem {

	// Hero
	private Hero hero;
	
	// Environment
	private Environment env;
	
	// Biodiversity
	private Biodiversity bio;
	
	// Ecosystem size
	private Vector2 size;
	
	// Gravity
	private float gravity;
	
	public Ecosystem() {
		this.env = new MainWorldMap(0, -9.8f);
		this.bio = new Biodiversity();
		this.hero = new Hero(200, 200);
		this.size = new Vector2(2835, 1134);
		this.gravity = -9.8f;
	}
	
	public void draw(SpriteBatch sb, float delta) {
		this.env.draw(sb);
		this.bio.draw(sb, delta);
		this.hero.draw(sb, delta);
		
	}

	public void draw(ShapeRenderer sr) {
		this.env.draw(sr);
		this.bio.draw(sr);
		this.hero.draw(sr);
	}

	public void updateColliding(LivingThing dynamicGameObject) {
		
		for (Solid s : this.env.solids) {
			// HORIZONTAL COLLIDING
			Hitbox hitboxH = dynamicGameObject.isCollapsingHorizontal(s.getBody());
			dynamicGameObject.applyHorizontalCollidingEffect(s.getBody(), hitboxH);
			
			// VERTICAL COLLIDING
			Hitbox hitboxV = dynamicGameObject.isCollapsingVertical(s.getBody());
			dynamicGameObject.applyVerticalCollidingEffect(s.getBody(), hitboxV);
		}
		if (dynamicGameObject.velocity.y != 0){
			dynamicGameObject.setStateValue("ENVIRONMENT", SubState.AIR);
		}
	}

	public void update(float delta) {
		
		//this.hero.update(this.gravity, delta);
		
		// DEATH FOR THE MOMENT
		if (this.hero.getY() < -100) {
			this.hero.setPosition(600, 300);
		}

		this.bio.update(this.gravity, delta);
		
		/*
		// INPUTS
		this.hero.updateInput();
		*/
	}

	
	public Vector2 getCameraPosition(float width, float height) {
		float heroX, heroY, camX, camY;
		heroX = this.hero.getCenterX();
		heroY = this.hero.getCenterY();
		
		if (heroX <= width) {
			camX = width;
		} 
		else if (heroX >= this.size.x - width) {
			camX = this.size.x - width;
		} 
		else {
			camX = heroX;
		}

		if (heroY <= height) {
			camY = height;
		} else if (heroY >= this.size.y - height) {
			camY = this.size.y - height;
		} else {
			camY = heroY;
		}
		return new Vector2(camX, camY);
	}
	
	/*
	public void drawEnvironement(ShapeRenderer sr){
		sr.rect(10,  10, this.size.x/5,  this.size.y/5, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
		for (int x = 0; x < this.size.x/10; x++){
			for (int y = 0; y < this.size.y/10; y++){
				int value = this.environement[x][y];
				if (value == 1){
					sr.rect(10 + x*2, 10 + y*2, 2, 2, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK);
				}
			}
		}
		sr.rect(10 + this.hero.getCenterX() / 5, 10 + this.hero.getY() / 5, 6, 6, Color.RED, Color.RED, Color.RED, Color.RED);
	}
	*/
	public void updateClimbing(Hero hero) {
		hero.canClimbUp = false;
		hero.canClimbDown = false;
		for (Ladder l : this.env.ladders) {
			if (hero.overlaps(l.climbZoneDown)) {
				hero.canClimbDown = true;
			}
			if (hero.overlaps(l.climbZone)) {
				hero.canClimbUp = true;
			}
			
		}
	}

	public boolean isOnWater(DynamicGameObject dynamicGameObject) {
		return this.env.isOnWater(dynamicGameObject);
	}
	
	public boolean isUnderWater(DynamicGameObject obj) {
		return this.env.isUnderWater(obj);
	}

	public void animate() {
		this.hero.live();
		this.bio.animate();
	}

}
