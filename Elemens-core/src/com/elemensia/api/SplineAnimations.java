package com.elemensia.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRendererDebug;

public class SplineAnimations extends Animation{

	private AnimationState animationState;
	private Skeleton skeleton;
	private SkeletonRenderer skeletonRenderer;
	private State state;
	private SkeletonRendererDebug debugRenderer;

	
	public SplineAnimations(String textureAtlasPath, String jsonPath) {
		this.skeletonRenderer = new SkeletonRenderer();
		this.skeletonRenderer.setPremultipliedAlpha(true);
		this.debugRenderer = new SkeletonRendererDebug();
		
		TextureAtlas playerAtlas = new TextureAtlas(Gdx.files.internal(textureAtlasPath));
		SkeletonJson json = new SkeletonJson(playerAtlas);
		json.setScale(0.3f);
		SkeletonData playerSkeletonData = json.readSkeletonData(Gdx.files.internal(jsonPath));
		AnimationStateData playerAnimationData = new AnimationStateData(playerSkeletonData);
		
		// Merge 2 animations for more smooth
		/*
		playerAnimationData.setMix("RUNNING", "JUMPING", 0.2f);
		playerAnimationData.setMix("JUMPING", "RUNNING", 0.2f);
		*/
		this.skeleton = new Skeleton(playerSkeletonData);
		this.animationState = new AnimationState(playerAnimationData);
		
		this.animationState.setTimeScale(0.4f); // Slow all animations down to 40% speed.
		this.setAnimation(State.IDLE, true);
	}
	
	public void update(float x, float y, float delta, State direction){
		this.animationState.update(delta);
		this.animationState.apply(this.skeleton);
		this.skeleton.setPosition(x, y);
		
		if (direction == State.LEFT){
			this.skeleton.setFlipX(true);
		}
		else if (direction == State.RIGHT){
			this.skeleton.setFlipX(false);
		}
			
		this.skeleton.updateWorldTransform();
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		this.skeletonRenderer.draw(batch, this.skeleton);
	}

	public void draw(ShapeRenderer sr) {
		this.debugRenderer.getShapeRenderer().setProjectionMatrix(sr.getProjectionMatrix());
		this.debugRenderer.draw(this.skeleton);
	}

	@Override
	public void setAnimation(State state, boolean loop) {
		if (!(this.state == state)){
			this.state = state;
			this.animationState.setAnimation(0, this.state.toString(), loop); // trackIndex, name, loop
		}
	}
}
