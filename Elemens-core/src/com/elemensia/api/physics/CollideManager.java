package com.elemensia.api.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class CollideManager {

	public CollideBox center, bottom, top, left, right;
	
	public CollideManager(int x, int y, int width, int height) {
		this.bottom = new CollideBox(x, 10, y, 0, width - 20, 10);
		this.top = new CollideBox(x, 10, y, height - 10, width - 20, 10);
		this.left = new CollideBox(x, 0,  y, 10, 10, height - 20);
		this.right = new CollideBox(x, width - 10, y ,10, 10, height - 20);
		this.center = new CollideBox(x, 20, y, 10, width - 40, height - 20);
	}
	
	public void setPosition(float x, float y){
		this.bottom.setPosition(x, y);
		this.top.setPosition(x, y);
		this.left.setPosition(x, y);
		this.right.setPosition(x, y);
		this.center.setPosition(x, y);
	}

	public void draw(ShapeRenderer sr) {
		this.left.draw(sr, Color.GREEN); 
		this.right.draw(sr, Color.GREEN);
		this.bottom.draw(sr, Color.BLUE);
		this.top.draw(sr, Color.BLUE);
		this.center.draw(sr, Color.RED);
	}

	public Hitbox isCollidingHorizontal(Rectangle r) {
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

	public Hitbox isCollidingVertical(Rectangle r) {
		if (this.bottom.overlaps(r) && (!this.top.overlaps(r) || this.center.overlaps(r))){
			return Hitbox.BOTTOM;
		}
		if (this.top.overlaps(r) && !this.bottom.overlaps(r)){
			return Hitbox.TOP;
		}
		if (this.center.overlaps(r)){
			return Hitbox.CENTER;
		}
		return Hitbox.NONE;
	}
	
	public float getCenterX() {
		return this.center.x + this.center.width / 2;
	}

	public float getCenterY() {
		return this.center.y + this.center.height / 2;
	}

	public CollideBox getCenterBox() {
		return this.center;
	}
}
