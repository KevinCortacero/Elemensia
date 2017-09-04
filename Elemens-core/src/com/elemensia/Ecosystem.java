package com.elemensia;

import com.Environment;
import com.MainWorldMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

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
		this.size = new Vector2(3000, 1000);
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

	public void updateColliding(DynamicGameObject dynamicGameObject) {
		for (Solid s : this.env.solids) {
			// HORIZONTAL COLLIDING
			Hitbox hitboxH = dynamicGameObject.collideManager.isCollidingHorizontal(s.getBody());
			dynamicGameObject.applyHorizontalCollidingEffect(s.getBody(), hitboxH);
			
			// VERTICAL COLLIDING
			Hitbox hitboxV = dynamicGameObject.collideManager.isCollidingVertical(s.getBody());
			dynamicGameObject.applyVerticalCollidingEffect(s.getBody(), hitboxV);
		}
	}

	public void update(float delta) {
		
		this.hero.update(this.env.gravity, delta);
		
		this.updateColliding(this.hero);

		// DEATH FOR THE MOMENT
		if (this.hero.getY() < -100) {
			this.hero.setPosition(600, 200);
		}

		for (Creature c : this.bio.creatures) {
			c.update(this.env.gravity, delta);
			this.updateColliding(c);
			c.takeDecision(delta);
		}
		
		// INPUTS
		this.hero.updateInput();
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
			if (hero.collideManager.getCenterBox().overlaps(l.climbZoneDown)) {
				hero.canClimbDown = true;
			}
			if (hero.collideManager.getCenterBox().overlaps(l.climbZone)) {
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

}
