package com.elemensia.api;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.elemensia.api.gameobjects.Creature;
import com.elemensia.api.gameobjects.Food;
import com.elemensia.game.Cube;
import com.elemensia.game.World;

public class Biodiversity {

	// Creatures
	private ArrayList<Creature> creatures;

	// Food
	private ArrayList<Food> foods;

	public Biodiversity() {
		// Creatures
		this.creatures = new ArrayList<Creature>();
		this.creatures.add(new Cube(1700, 800));
		this.creatures.add(new Cube(1500, 800));
		this.creatures.add(new Cube(1300, 800));
		this.creatures.add(new Cube(1100, 800));
		this.creatures.add(new Cube(400, 400));
		this.creatures.add(new Cube(600, 400));
		this.creatures.add(new Cube(800, 400));
		this.creatures.add(new Cube(1000, 400));

		// Foods
		this.foods = new ArrayList<Food>();
		this.foods.add(new Food(1800, 300));
	}
	
	public void animate(){
		for (Creature c : this.creatures){
			c.live();
		}
	}
	
	public void draw(SpriteBatch sb, float delta){
		for (Food f : this.foods)
			f.draw(sb, delta);
		for (Creature c : this.creatures)
			c.draw(sb, delta);
	}

	public void draw(ShapeRenderer sr){
		for (Creature c : this.creatures)
			c.draw(sr);
		for (Food f : this.foods)
			f.draw(sr);
	}

	public void update(float gravity, float delta) {
		for (Food f : this.foods){
			f.update(gravity, delta);
			World.updateColliding(f);
		}
	}


}
