package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class World implements Disposable {

	private Hero hero;
	private ArrayList<Solid> solids;
	private ArrayList<Ladder> ladders;
	private ArrayList<Solid> water;
	private Texture background;
	private Texture foreground;
	private Vector2 gravity;

	public World(float x, float y) {
		this.gravity = new Vector2(x, y);
		this.background = new Texture("village_back.png");
		this.foreground = new Texture("village_front.png");
		this.solids = new ArrayList<Solid>();
		this.solids.add(new Solid(0, 0, 2000, 140));
		this.solids.add(new Solid(0, 140, 20, 1000));
		this.solids.add(new Solid(50, 200, 200, 50));
		this.solids.add(new Solid(600, 140, 40, 400));
		this.solids.add(new Solid(2000, 140, 40, 400));
		
		// Water zone
		this.water = new ArrayList<Solid>();
		this.water.add(new Solid(640, 140, 1360, 380));

		// Ladder
		this.ladders = new ArrayList<Ladder>();
		this.ladders.add(new Ladder(372, 140, 80, 250));

		this.solids.add(this.ladders.get(0).top);

		/* Steps
		for (int i = 0; i < 1000; i++) {
			this.solids.add(new Solid(800 + i * 20, 140 + i * 10, 20, 1));
		}*/
		//this.hero = new Hero(200, 200, 74, 107);
		this.hero = new Hero(200, 200, 50, 50);
	}

	public void draw(SpriteBatch sb, float delta) {
		sb.draw(this.background, 0, 0);
		this.hero.draw(sb, delta);
		sb.draw(this.foreground, 0, 0);
	}

	public void draw(ShapeRenderer sr) {
		this.hero.draw(sr);
		for (Solid s : this.solids)
			s.draw(sr, Color.RED);
		for (Solid s : this.water)
			s.draw(sr, Color.BLUE);
		for (Ladder s : this.ladders)
			s.draw(sr);
	}

	public void update() {
		this.hero.update((float)Math.min(Gdx.graphics.getDeltaTime(), 0.035), this.gravity, this.water, this.ladders);
		
		
		
		for (Solid s : this.solids) {

			switch (this.hero.isCollidingHorizontal(s.body)) {
			case CENTER:
				break;
			case LEFT:
				this.hero.stopH(s.body.x + s.body.width + 1);
				break;
			case RIGHT:
				this.hero.stopH(s.body.x - this.hero.body.width - 1);
				break;
			default:
				break;
			}

			switch (this.hero.isCollidingVertical(s.body)) {
			case CENTER:
				break;
			case BOTTOM:
				if (!this.hero.canClimbDown) {
					this.hero.resetJump();
					this.hero.stopV(s.body.y + s.body.height);
				}
				break;
			case TOP:
				if (!this.hero.canClimbUp) {
					this.hero.stopV(s.body.y - this.hero.body.height);
				}
				break;
			default:
				break;
			}
		}

		this.hero.updateInput();

		// DEATH
		if (this.hero.body.y < -100) {
			this.hero.setPosition(600, 200);
		}
	}

	@Override
	public void dispose() {
		this.background.dispose();
		this.hero.dispose();
	}

	public Vector2 getCameraPosition() {
		float heroX, heroY, camX, camY;
		heroX = this.hero.getCenterX(); 
		heroY = this.hero.getCenterY(); 
		if (heroX <= 800) {
			camX = 800;
		} else if (heroX >= 6880) {
			camX = 6880;
		} else {
			camX = heroX;
		}

		if (heroY <= 450) {
			camY = 450;
		} else if (heroY >= 3870) {
			camY = 3870;
		} else {
			camY = heroY;
		}
		return new Vector2(camX, camY);
	}
}
