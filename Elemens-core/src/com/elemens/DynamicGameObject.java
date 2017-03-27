package com.elemens;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public abstract class DynamicGameObject extends GameObject implements Disposable {

	private static final String[] STATES = {"IDLE_RIGHT", "IDLE_LEFT", "WALK_RIGHT", "WALK_LEFT", 
											"JUMP_RIGHT", "JUMP_LEFT", "SWIM_RIGHT", "SWIM_LEFT", "CLIMB"};
	private Map<String, Animation<TextureRegion>> animations;
	protected Texture sprite;
	protected float velocityY;
	private int jumpCount;
	private float timer;
	private String state;

	public DynamicGameObject(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.sprite = new Texture("Blizz.png");
		this.state = STATES[0];
		// new Sprite(new TextureRegion(, 50, 0, 50, 50));
		this.velocityY = 0;
		this.jumpCount = 1;
		this.timer = 0;

		
		TextureRegion[][] tmp = TextureRegion.split(this.sprite, width, height);

		this.animations = new HashMap<String, Animation<TextureRegion>>();
		for (int j = 0; j < STATES.length; j++){
			TextureRegion[] animation = new TextureRegion[6];
			for (int i = 0; i < 6; i++) {
				animation[i] = tmp[j][i];
			}
			this.animations.put(STATES[j], new Animation<TextureRegion>(0.15f, animation));
		}
	}

	public boolean isMovingUp() {
		return (this.velocityY > 0);
	}

	public void stopV(float height) {
		this.velocityY = 0;
		this.setPosition(this.body.x, height);
	}

	public void stopH(float width) {
		this.setPosition(width, this.body.y);
	}

	public void setPosition(float x, float y) {
		this.body.x = x;
		this.body.y = y;
	}

	public void moveRight(float delta) {
		this.body.x += (250 * delta);
		this.state = STATES[2];
	}

	public void moveLeft(float delta) {
		this.body.x -= (250 * delta);
		this.state = STATES[3];
	}

	public void climbUp() {
		this.velocityY = 5;
		this.state = STATES[8];
	}

	public void climbDown() {
		this.velocityY = -5;
		this.state = STATES[8];
	}

	public void jump() {
		if (this.jumpCount > 0) {
			this.velocityY = 10;
			this.jumpCount--;
		}
	}

	public void resetJump() {
		this.jumpCount = 1;
	}

	public void draw(SpriteBatch batch, float delta) {
		this.timer += delta;
		batch.draw(this.animations.get(this.state).getKeyFrame(this.timer, true), this.body.x, this.body.y);
	}

	@Override
	public void dispose() {
		this.sprite.dispose();
	}

}
