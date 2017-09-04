package com;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.elemens.CollideBox;
import com.elemens.Creature;
import com.elemens.DynamicGameObject;
import com.elemens.Hero;
import com.elemens.Ladder;
import com.elemens.Solid;
import com.elemens.Utility;
import com.elemens.WaterArea;

public abstract class Environment {

	private int width;
	private int height;

	public ArrayList<Solid> solids;
	public ArrayList<Ladder> ladders;

	public ArrayList<WaterArea> water;
	private Texture background;
	private Texture npc;
	// private Texture foreground;
	public Vector2 gravity;

	private int[][] environement;

	public Environment(float x, float y) {
		this.gravity = new Vector2(x, y);
		this.width = 2835;
		this.height = 1134;
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
		for (int x = (int) (solid.getX() / 10); x < (solid.getX() + solid.getWidth()) / 10 -1; x ++){
			for (int y = (int) (solid.getY() / 10); y < (solid.getY() + solid.getHeight()) / 10 -1; y ++){
				this.environement[x][y] = 1;
			}
		}
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
