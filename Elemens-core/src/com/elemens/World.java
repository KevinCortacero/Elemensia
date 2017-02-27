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

	public World() {
		this.background = new Texture("village.png");
		this.solids = new ArrayList<Solid>();
		this.solids.add(new Solid(0, 0, 2000, 140));
		this.solids.add(new Solid(0, 140, 20, 1000));
		this.solids.add(new Solid(50, 200, 200, 50));
		this.solids.add(new Solid(360, 380, 94, 20));
		this.ladders = new ArrayList<Solid>();
		this.ladders.add(new Solid(372, 140, 70, 260));
		this.hero = new Hero(200, 100, 74, 107);
	}
	
	public void draw(SpriteBatch sb){
		sb.draw(this.background, 0, 0);
		this.hero.draw(sb);
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
		boolean isClimbing = false;
		for (Solid l : this.ladders){
			if (this.hero.isColliding(l.body) == Hitbox.CENTER){
				isClimbing = true;
				break;
			}
		}
		if (!isClimbing){
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
					this.hero.stopH(s.body.x + s.body.width +1);
					break;
				case RIGHT:
					this.hero.stopH(s.body.x - this.hero.body.width -1);
					break;
				case TOP:
					this.hero.stopV(s.body.y - this.hero.body.height);
					break;
				
				}
			}
		}
		
		this.hero.updateInput();
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
