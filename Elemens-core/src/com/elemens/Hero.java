package com.elemens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Hero extends DynamicGameObject {

	private Rectangle bottom, top, left, right;
	Rectangle center;
	boolean canClimb;

	public Hero(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.bottom = new Rectangle(x + 10, y, width - 20, 10);
		this.top = new Rectangle(x + 10, y + height - 10, width - 20, 10);
		this.left = new Rectangle(x, y + 20, 10, height - 40);
		this.right = new Rectangle(x + width - 10, y + 20, 10, height - 40);
		this.center = new Rectangle(x + 10, y + 20, width - 20, height - 30);
		this.canClimb = false;
	}

	public Hitbox isColliding(Rectangle r) {
		if (this.center.overlaps(r))
			return Hitbox.CENTER;
		if (this.bottom.overlaps(r)) {
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
		this.bottom.setPosition(x + 10, y);
		this.top.setPosition(x + 10, y + this.body.height - 10);
		this.left.setPosition(x, y + 20);
		this.right.setPosition(x + this.body.width - 10, y + 20);
		this.center.setPosition(x + 10, y + 20);
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
		if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
			if (this.canClimb) {
				this.climb();
			}
			this.climb();
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

}
