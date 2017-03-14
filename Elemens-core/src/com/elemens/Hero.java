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
	public CollideBox waterBox;

	public Hero(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.bottom = new CollideBox(x, 10, y, 0, width - 20, 10);
		this.top = new CollideBox(x, 10, y, height - 10, width - 20, 10);
		this.left = new CollideBox(x, 0,  y, 10, 10, height - 20);
		this.right = new CollideBox(x, width - 10, y ,10, 10, height - 20);
		this.center = new CollideBox(x, 20, y, 10, width - 40, height - 20);
		this.waterBox = new CollideBox(x, 0, y , height*2/3, width, 20);
		this.canClimbUp = false;
		this.canClimbDown = false;
		this.isOnWater = false;
		this.isUnderWater = false;
	}

	public void update(float delta, Vector2 gravity){
		if (!this.canClimbUp && !this.isUnderWater){
			this.velocityY += (gravity.y*delta*2.5);
		}
		if (this.isUnderWater){
			this.velocityY *= 0.95;
			if (this.velocityY < 0.5 && this.velocityY > -0.5){
				this.resetJump();
			}
		}

		if (this.isOnWater){
			this.velocityY -= (gravity.y*delta*1.25);			
		}

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
		this.waterBox.setPosition(x,y);
	}

	public void draw(ShapeRenderer sr) {
		this.body.draw(sr, Color.GOLD);
		this.left.draw(sr, Color.GREEN); 
		this.right.draw(sr, Color.GREEN);
		this.bottom.draw(sr, Color.BLUE);
		this.top.draw(sr, Color.BLUE);
		this.center.draw(sr, Color.RED);
		this.waterBox.draw(sr, Color.ORANGE);
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
		return (this.waterBox.overlaps(water));
	}

}
