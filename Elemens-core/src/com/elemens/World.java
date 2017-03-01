package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class World implements Disposable{

	private Hero hero;
	private ArrayList<Solid> solids;
	private ArrayList<Solid> ladders;
	private Texture background;
	private Texture foreground;

	public World() {
		this.background = new Texture("village_back.png");
		this.foreground = new Texture("village_front.png");
		this.solids = new ArrayList<Solid>();
		this.solids.add(new Solid(0, 0, 2000, 140));
		this.solids.add(new Solid(0, 140, 20, 1000));
		this.solids.add(new Solid(50, 200, 200, 50));
		
		// Ladder
		this.solids.add(new Solid(372, 396, 70, 4));
		this.solids.add(new Solid(302, 140, 70, 260));
		this.solids.add(new Solid(442, 380, 70, 20));
		this.ladders = new ArrayList<Solid>();
		this.ladders.add(new Solid(372, 140, 70, 260));
		
		// Steps
		/*for (int i = 0; i < 1000; i++){
			this.solids.add(new Solid(800+i*2, 150+i, 2, 1));
		}*/
		this.hero = new Hero(600, 200, 74, 107);
	}

	public void draw(SpriteBatch sb){
		sb.draw(this.background, 0, 0);
		this.hero.draw(sb);
		sb.draw(this.foreground, 0, 0);
	}

	public void draw(ShapeRenderer sr){
		this.hero.draw(sr);
		for (Solid s : this.solids)
			s.draw(sr);
		for (Solid s : this.ladders)
			s.draw(sr);
	}

	public void update(){
		this.hero.update(Gdx.graphics.getDeltaTime());
		this.hero.canClimb = false;
		for (Solid l : this.ladders){
			if (this.hero.center.overlaps(l.body)){
				this.hero.canClimb = true;
				break;
			}
		}
		for (Solid s : this.solids){
			switch(this.hero.isColliding(s.body)){
			case NONE:
				break;
			case CENTER:
				break;
			case BOTTOM:
				this.hero.stopV(s.body.y + s.body.height);
				break;
			case LEFT:
				if (!this.hero.canClimb)
					this.hero.stopH(s.body.x + s.body.width +1);
				break;
			case RIGHT:
				if (!this.hero.canClimb)
					this.hero.stopH(s.body.x - this.hero.body.width -1);
				break;
			case TOP:
				if (!this.hero.canClimb)
					this.hero.stopV(s.body.y - this.hero.body.height);
				break;
			}
		}

		this.hero.updateInput();
		
		if (this.hero.body.y < -100){
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
		heroX = this.hero.getCenterX(); // this.hero.body.x + this.hero.body.width/2
		heroY = this.hero.getCenterY(); // this.hero.body.y + this.hero.body.height/2
		if (heroX <= 800){
			camX = 800;
		}
		else if (heroX >= 6880){
			camX = 6880;
		}
		else{
			camX = heroX;
		}

		if (heroY <= 450){
			camY = 450;
		}
		else if(heroY >= 3870){
			camY = 3870;
		}
		else{
			camY = heroY;
		}
		return new Vector2(camX, camY);
	}
}
