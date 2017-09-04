package com.elemens;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SpriteAnimation extends com.elemens.Animation{
	
	private Map<State, Animation<TextureRegion>> animations;
	private Texture sprite;
	private TextureRegion currentTexture;
	private State state;
	private float x;
	private float y;
	private float timer;
	
	public SpriteAnimation(int width, int height, State[] states, String spritePath) {
		this.sprite = Utility.getTextureAsset(spritePath);
		TextureRegion[][] tmp = TextureRegion.split(this.sprite, width, height);

		this.animations = new HashMap<State, Animation<TextureRegion>>();
		for (int j = 0; j < states.length; j++){
			TextureRegion[] animation = new TextureRegion[tmp[0].length];
			for (int i = 0; i < tmp[0].length; i++) {
				animation[i] = tmp[j][i];
			}
			this.animations.put(states[j], new Animation<TextureRegion>(0.1f, animation));
		}
		this.setAnimation(states[0], true);
	}
	
	/*
	public TextureRegion getCurrentAnimation(String state, float timer) {
		return 
	}
	*/

	public void dispose() {
		this.sprite.dispose();
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(this.currentTexture, this.x, this.y);
	}

	@Override
	public void setAnimation(State state, boolean loop) {
		this.currentTexture = this.animations.get(state).getKeyFrame(this.timer, loop);
		this.state = state;
	}

	@Override
	public void update(float x, float y, float delta, Direction direction) {
		this.x = x - 32;
		this.y = y;
		this.timer += delta;
		this.setAnimation(this.state, true);
	}

	@Override
	public void draw(ShapeRenderer sr) {
		// TODO Auto-generated method stub
		
	}
}
