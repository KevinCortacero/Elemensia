package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Biodiversity {
	
	// Creatures
	ArrayList<Creature> creatures;
		
	public Biodiversity() {
		this.creatures = new ArrayList<Creature>();
		this.creatures.add(new Twarzian(1700, 800));
		this.creatures.add(new Cube(400, 400));
	}
	
	public void draw(SpriteBatch sb, float delta){
		for (Creature c : this.creatures)
			c.draw(sb, delta);
	}
	
	public void draw(ShapeRenderer sr){
		for (Creature c : this.creatures)
			c.draw(sr);
	}

	
}
