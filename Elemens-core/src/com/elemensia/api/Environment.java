package com.elemensia.api;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.elemensia.api.gameobjects.DynamicGameObject;
import com.elemensia.api.gameobjects.Food;
import com.elemensia.api.gameobjects.Ladder;
import com.elemensia.api.gameobjects.Solid;
import com.elemensia.api.gameobjects.WaterArea;

public abstract class Environment {

	private int width;
	private int height;

	public ArrayList<Solid> solids;
	public ArrayList<Ladder> ladders;

	public ArrayList<WaterArea> water;

	private Texture background;
	private Texture npc;
	// private Texture foreground;

	private int[][] environement;

	public Environment(float x, float y) {
		this.environement =  new int[this.width / 10][this.height / 10];
		this.background = Utility.getTextureAsset("world/bg.jpg");
		this.npc = Utility.getTextureAsset("living_things/npc/npc.png");

		// Solids
		this.solids = new ArrayList<Solid>();
		this.loadSolids();
		// Water zone
		this.water = new ArrayList<WaterArea>();

		// Ladder
		this.ladders = new ArrayList<Ladder>();
		this.ladders.add(new Ladder(885, 280, 80, 430));
		this.addSolid(this.ladders.get(0).top);

	}	

	public void draw(SpriteBatch sb){
		sb.draw(this.background, 0, 0);
		sb.draw(this.npc, 600, 270);
		
	}

	public void draw(ShapeRenderer sr) {
		for (Solid s : this.solids)
			s.draw(sr);
		for (WaterArea w : this.water)
			w.draw(sr);
		for (Ladder s : this.ladders)
			s.draw(sr);
	}

	protected void addSolid(Solid solid){
		this.solids.add(solid);
		/*
		for (int x = (int) (solid.getX() / 10); x < (solid.getX() + solid.getWidth()) / 10 -1; x ++){
			for (int y = (int) (solid.getY() / 10); y < (solid.getY() + solid.getHeight()) / 10 -1; y ++){
				this.environement[x][y] = 1;
			}
		}
		 */
	}

	public boolean isUnderWater(DynamicGameObject obj) {
		for (WaterArea w : this.water) {
			if (w.overlaps(obj.getWaterBox())) {
				return true;
			}
		}
		return false;
	}

	public boolean isOnWater(DynamicGameObject object) {
		for (WaterArea w : this.water) {
			if (object.getBody().overlaps(w.getBody())) {
				return true;
			}
		}
		return false;
	}

	public abstract void loadSolids();
}
