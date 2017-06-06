package com.elemens;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteAnimation {
	
	private Map<String, Animation<TextureRegion>> animations;
	protected Texture sprite;
	private float timer;
	private String defaultState;
	
	public SpriteAnimation(int width, int height, String[] states, String spritePath) {
		this.sprite = Utility.getTextureAsset(spritePath);
		this.timer = 0;
		this.defaultState = states[0];
		TextureRegion[][] tmp = TextureRegion.split(this.sprite, width, height);

		this.animations = new HashMap<String, Animation<TextureRegion>>();
		for (int j = 0; j < states.length; j++){
			TextureRegion[] animation = new TextureRegion[tmp[0].length];
			for (int i = 0; i < tmp[0].length; i++) {
				animation[i] = tmp[j][i];
			}
			this.animations.put(states[j], new Animation<TextureRegion>(0.15f, animation));
		}
		Gdx.app.debug("SpriteAnimation", "animations loaded " + spritePath + " !");
	}

	public void reset() {
		this.timer = 0;
	}

	public void update(float delta) {
		this.timer += delta;
	}

	public TextureRegion getCurrentAnimation(String state) {
		return this.animations.get(state).getKeyFrame(this.timer, true);
	}

	public void dispose() {
		this.sprite.dispose();
	}

	public String getDefaultState() {
		return this.defaultState;
	}
}
