package com.elemens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Hero extends DynamicGameObject{
	
	private Rectangle bottom, top, left, right, center;
	
	public Hero(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.bottom = new Rectangle(x+10, y, width-20, height/4);
		this.top    = new Rectangle(x+10, y + height*3/4, width- 20, height/4);
		this.left   = new Rectangle(x, y + 10, width/4, height - 20);
		this.right  = new Rectangle(x + width*3/4, y + 10, width/4, height - 20);
		this.center = new Rectangle(x + width/4, y + height/4, width/2, height/2);
	}
	
	public Hitbox isColliding(Rectangle r){
		if (this.center.overlaps(r))
			return Hitbox.CENTER;
		if (this.bottom.overlaps(r)){
			this.resetJump();
			return Hitbox.BOTTOM;
		}
		if (this.left.overlaps(r))
			return Hitbox.LEFT;
		if (this.right.overlaps(r))
			return Hitbox.RIGHT;
		if (this.top.overlaps(r))
			return Hitbox.TOP;
		return Hitbox.NONE;
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.bottom.setPosition(x+10, y);
		this.top.setPosition(x+10, y + this.body.height*3/4);
		this.left.setPosition(x, y + 10);
		this.right.setPosition(x + this.body.width*3/4, y + 10);
		this.center.setPosition(x + this.body.width/4, y + this.body.height/4);
	}

	public void draw(ShapeRenderer sr) {
		//this.drawHitBox(sr, this.body, Color.GOLD);
		this.drawHitBox(sr, this.left, Color.GREEN);
		this.drawHitBox(sr, this.right, Color.GREEN);
		this.drawHitBox(sr, this.bottom, Color.BLUE);
		this.drawHitBox(sr, this.top, Color.BLUE);
		this.drawHitBox(sr, this.center, Color.RED);
	}
	
	private void drawHitBox(ShapeRenderer sr, Rectangle r, Color c){
		sr.rect(r.x, r.y, r.width, r.height, c, c, c, c);
	}

	public void updateInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.Z)){
			this.climb();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			this.moveRight(Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q)){
			this.moveLeft(Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			this.jump();
		}
	}

	public float getCenterX() {
		return this.body.x + this.body.width/2;
	}
	
	public float getCenterY() {
		return this.body.y + this.body.height/2;
	}
	
}
