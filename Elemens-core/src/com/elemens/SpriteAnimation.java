package com.elemens;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteAnimation {
	
	private Map<String, Animation<TextureRegion>> animations;
	private Texture sprite;
	private String defaultState;
	
	public SpriteAnimation(int width, int height, String[] states, String spritePath) {
		this.sprite = Utility.getTextureAsset(spritePath);
		this.defaultState = states[0];
		TextureRegion[][] tmp = TextureRegion.split(this.sprite, width, height);

		this.animations = new HashMap<String, Animation<TextureRegion>>();
		for (int j = 0; j < states.length; j++){
			TextureRegion[] animation = new TextureRegion[tmp[0].length];
			for (int i = 0; i < tmp[0].length; i++) {
				animation[i] = tmp[j][i];
			}
			this.animations.put(states[j], new Animation<TextureRegion>(0.3f, animation));
		}
	}

	public TextureRegion getCurrentAnimation(String state, float timer) {
		return this.animations.get(state).getKeyFrame(timer, true);
	}

	public void dispose() {
		this.sprite.dispose();
	}

	public String getDefaultState() {
		return this.defaultState;
	}
}
