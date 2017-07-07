package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class World implements Disposable {

	private Hero hero;
	private ArrayList<Solid> solids;
	private ArrayList<Ladder> ladders;
	private ArrayList<Creature> creatures;
	private ArrayList<WaterArea> water;
	private Texture background;
	//private Texture foreground;
	private Vector2 gravity;
	
	private static World world;
	
	public static World create(float x, float y) {
		if (world == null)
			world = new World(x, y);
		return world;
	}

	private World(float x, float y) {
		this.gravity = new Vector2(x, y);
		this.background = Utility.getTextureAsset("village_back.png");
		//this.foreground = Utility.getTextureAsset("village_front.png");
		
		// Solids
		this.solids = new ArrayList<Solid>();
		this.solids.add(new Solid(0, 0, 3000, 140));
		this.solids.add(new Solid(0, 140, 20, 1000));
		this.solids.add(new Solid(50, 200, 200, 50));
		this.solids.add(new Solid(600, 140, 40, 400));
		this.solids.add(new Solid(2000, 140, 40, 400));

		// Water zone
		this.water = new ArrayList<WaterArea>();
		this.water.add(new WaterArea(640, 140, 1360, 380));
		this.water.add(new WaterArea(5150, 140, 300, 2000));

		// Ladder
		this.ladders = new ArrayList<Ladder>();
		this.ladders.add(new Ladder(372, 140, 80, 250));
		this.solids.add(this.ladders.get(0).top);

		this.ladders.add(new Ladder(520, 140, 80, 350));
		this.solids.add(this.ladders.get(1).top);


		this.creatures = new ArrayList<Creature>();
		this.creatures.add(new Twarzian(700, 800));
		this.creatures.add(new Twarzian(850, 1000));
		this.creatures.add(new Twarzian(1000, 1200));
		this.creatures.add(new Twarzian(1150, 1400));
		this.creatures.add(new Twarzian(1300, 1600));
		this.creatures.add(new Twarzian(2350, 2000));
		/* Steps
		for (int i = 0; i < 1000; i++) {
			this.solids.add(new Solid(800 + i * 20, 140 + i * 10, 20, 1));
		}*/
		//this.hero = new Hero(200, 200, 74, 107);
		this.hero = new Hero(200, 200);
	}

	public void draw(SpriteBatch sb, float delta) {
		sb.draw(this.background, 0, 0);
		for(Creature c : this.creatures)
			c.draw(sb, delta);
		this.hero.draw(sb, delta);
		//sb.draw(this.foreground, 0, 0);
	}

	public void draw(ShapeRenderer sr) {
		for (Solid s : this.solids)
			s.draw(sr);
		for (WaterArea w : this.water)
			w.draw(sr);
		for (Ladder s : this.ladders)
			s.draw(sr);
		for(Creature c : this.creatures)
			c.draw(sr);
		this.hero.draw(sr);
	}
	

	public static void updateHorizontalColliding(DynamicGameObject dynamicGameObject) {
		for (Solid s : world.solids){
			Hitbox hitbox = dynamicGameObject.collideManager.isCollidingHorizontal(s.getBody());
			dynamicGameObject.applyHorizontalCollidingEffect(s.getBody(), hitbox);
		}
	}
	
	public static void updateVerticalColliding(DynamicGameObject dynamicGameObject) {
		for (Solid s : world.solids){
			Hitbox hitbox = dynamicGameObject.collideManager.isCollidingVertical(s.getBody());
			dynamicGameObject.applyVerticalCollidingEffect(s.getBody(), hitbox);
		}
	}
	
	
	public void update(float delta) {
		this.hero.update(delta, this.gravity, this.ladders);
		this.hero.updateColliding();

		// DEATH
		if (this.hero.getY() < -100) {
			this.hero.setPosition(600, 200);
		}
		for(Creature c : this.creatures){
			c.update(delta, this.gravity, false);
			c.updateColliding();
			c.takeDecision(delta);
		}
		this.hero.updateInput();
	}

	@Override
	public void dispose() {
		this.background.dispose();
		//this.foreground.dispose();
		this.hero.dispose();
		for (Creature c : this.creatures){
			c.dispose();
		}
	}

	public Vector2 getCameraPosition(float width, float height) {
		float heroX, heroY, camX, camY;
		heroX = this.hero.getCenterX(); 
		heroY = this.hero.getCenterY(); 
		if (heroX <= width) {
			camX = width;
		} else if (heroX >= 3840 - width) {
			camX = 3840 - width;
		} else {
			camX = heroX;
		}

		if (heroY <= height) {
			camY = height;
		} else if (heroY >= 2160 - height) {
			camY = 2160 - height;
		} else {
			camY = heroY;
		}
		return new Vector2(camX, camY);
	}
	
	public static boolean isUnderWater(DynamicGameObject object){
		for (WaterArea w : world.water) {
			if (w.contains(object)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isOnWater(DynamicGameObject object) {
		for (WaterArea w : world.water) {
			if (object.getBody().overlaps(w.getBody())){
				return true;
			}
		}
		return false;
	}

	
}
