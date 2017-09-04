package com.elemensia;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class World {

	// Singleton
	private static World world;
	private Ecosystem ecosystem;

	public static World create(float x, float y) {
		if (world == null)
			world = new World(x, y);
		return world;
	}

	private World(float x, float y) {
		this.ecosystem = new Ecosystem();
	}

	public void draw(SpriteBatch sb, float delta) {
		this.ecosystem.draw(sb, delta);
	}

	public void draw(ShapeRenderer sr) {
		this.ecosystem.draw(sr);
	}

	public static void updateClimbing(Hero hero) {
		world.ecosystem.updateClimbing(hero);
	}

	public void update(float delta) {
		this.ecosystem.update(delta);
	}

	public Vector2 getCameraPosition(float width, float height) {
		return this.ecosystem.getCameraPosition(width, height);
	}

	public static boolean isOnWater(DynamicGameObject dynamicGameObject) {
		return world.ecosystem.isOnWater(dynamicGameObject);
	}

	public static boolean isUnderWater(DynamicGameObject obj) {
		return world.ecosystem.isUnderWater(obj);
	}

}
