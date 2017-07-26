package com.elemens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;

public class SplineAnimations {

	private AnimationState animationState;
	private Skeleton skeleton;
	private SkeletonRenderer skeletonRenderer;
	private State state;
	
	public SplineAnimations(String textureAtlasPath, String jsonPath) {
		this.skeletonRenderer = new SkeletonRenderer();
		this.skeletonRenderer.setPremultipliedAlpha(true);
		
		
		TextureAtlas playerAtlas = new TextureAtlas(Gdx.files.internal(textureAtlasPath));
		SkeletonJson json = new SkeletonJson(playerAtlas);
		json.setScale(0.3f);
		SkeletonData playerSkeletonData = json.readSkeletonData(Gdx.files.internal(jsonPath));
		AnimationStateData playerAnimationData = new AnimationStateData(playerSkeletonData);
		
		// Merge 2 animations for more smooth
		playerAnimationData.setMix("RUNNING", "JUMPING", 0.2f);
		playerAnimationData.setMix("JUMPING", "RUNNING", 0.2f);
		
		this.skeleton = new Skeleton(playerSkeletonData);
		this.animationState = new AnimationState(playerAnimationData);
		
		this.animationState.setAnimation(0, "IDLE", true); // trackIndex, name, loop
	}
	
	public void update(float x, float y, float delta, Direction direction){
		this.animationState.update(delta);
		this.animationState.apply(this.skeleton);
		this.skeleton.setPosition(x, y);
		
		if (direction == Direction.LEFT){
			this.skeleton.setFlipX(true);
		}
		else if (direction == Direction.RIGHT){
			this.skeleton.setFlipX(false);
		}
			
		this.skeleton.updateWorldTransform();
	}
	
	public void draw(SpriteBatch batch) {
		this.skeletonRenderer.draw(batch, this.skeleton);
	}
	
	public void setAnimation(State state, int index, boolean loop){
		if (!(this.state == state)){
			this.state = state;
			this.animationState.setAnimation(index, this.state.toString(), loop); // trackIndex, name, loop
		}
	}
}
