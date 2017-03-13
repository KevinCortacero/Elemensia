package com.elemens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Hero extends DynamicGameObject {

	public CollideBox center, bottom, top, left, right;
	boolean canClimbUp;
	boolean canClimbDown;
	public boolean isOnWater;
	public boolean isUnderWater;

	public Hero(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.bottom = new CollideBox(x, 10, y, 0, width - 20, 10);
		this.top = new CollideBox(x, 10, y, height - 10, width - 20, 10);
		this.left = new CollideBox(x, 0,  y, 10, 10, height - 20);
		this.right = new CollideBox(x, width - 10, y ,10, 10, height - 20);
		this.center = new CollideBox(x, 20, y, 10, width - 40, height - 20);
		this.canClimbUp = false;
		this.canClimbDown = false;
		this.isOnWater = false;
		this.isUnderWater = false;
	}

	public void update(float delta, Vector2 gravity){
		if (!this.canClimbUp && !this.isOnWater){
			this.velocityY += (gravity.y*delta*2.5);
		}
		else if (this.isOnWater){
			this.velocityY += (gravity.y*delta*0.5);
		}
		if (this.isUnderWater)
			this.velocityY -= (gravity.y*delta*2);
		this.body.y += this.velocityY;
		this.setPosition(body.x, body.y);
	}

	public Hitbox isCollidingH(Rectangle r) {
		if (this.left.overlaps(r) && !this.right.overlaps(r) && !this.center.overlaps(r)){
			return Hitbox.LEFT;
		}
		if (this.right.overlaps(r) && !this.left.overlaps(r) && !this.center.overlaps(r)){
			return Hitbox.RIGHT;
		}
		if (this.center.overlaps(r)){
			return Hitbox.CENTER;
		}
		return Hitbox.NONE;
	}

	public Hitbox isCollidingV(Rectangle r) {
		if (this.bottom.overlaps(r) && (!this.top.overlaps(r) || this.center.overlaps(r))){
			return Hitbox.BOTTOM;
		}
		if (this.top.overlaps(r) && !this.bottom.overlaps(r) && this.isMovingUp()){
			return Hitbox.TOP;
		}
		if (this.center.overlaps(r)){
			return Hitbox.CENTER;
		}
		return Hitbox.NONE;
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.bottom.setPosition(x, y);
		this.top.setPosition(x, y);
		this.left.setPosition(x, y);
		this.right.setPosition(x, y);
		this.center.setPosition(x, y);
	}

	public void draw(ShapeRenderer sr) {
		// this.drawHitBox(sr, this.body, Color.GOLD);
		this.drawHitBox(sr, this.left, Color.GREEN);
		this.drawHitBox(sr, this.right, Color.GREEN);
		this.drawHitBox(sr, this.bottom, Color.BLUE);
		this.drawHitBox(sr, this.top, Color.BLUE);
		this.drawHitBox(sr, this.center, Color.RED);
	}

	private void drawHitBox(ShapeRenderer sr, Rectangle r, Color c) {
		sr.rect(r.x, r.y, r.width, r.height, c, c, c, c);
	}

	public void updateInput() {
		if (this.canClimbUp) {
			this.climbUp();
		}
		if (this.canClimbDown) {
			this.climbDown();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			this.moveRight(Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			this.moveLeft(Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			this.jump();
		}
	}

	public float getCenterX() {
		return this.body.x + this.body.width / 2;
	}

	public float getCenterY() {
		return this.body.y + this.body.height / 2;
	}

	public boolean isOnWater(Rectangle water) {
		return (this.body.overlaps(water));
	}

	public boolean isUnderWater(Rectangle water) {
		return (this.top.overlaps(water));
	}

}
