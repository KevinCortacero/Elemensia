package com.elemens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;

public class Hero extends LivingThing {

	public static final String[] HERO_STATES = {"IDLE_RIGHT", "IDLE_LEFT", "WALK_RIGHT", "WALK_LEFT", 
			"JUMP_RIGHT", "JUMP_LEFT", "SWIM_RIGHT", "SWIM_LEFT", "CLIMB"};

	public static final String HERO_SPRITE_PATH = "blizz.png";
	private static SpriteAnimation sprite = new SpriteAnimation(50, 50, HERO_STATES, HERO_SPRITE_PATH);
	boolean canClimbUp;
	boolean canClimbDown;
	private String current_animation;
	
	private AnimationState animationState;

	private Skeleton skeleton;

	private SkeletonRenderer skeletonRenderer;

	public Hero(int x, int y) {               
		super(x, y, 50, 50, sprite, 100);
		this.canClimbUp = false;
		this.canClimbDown = false;
		
		TextureAtlas playerAtlas = new TextureAtlas(Gdx.files.internal("spineboy-pma.atlas"));
		SkeletonJson json = new SkeletonJson(playerAtlas);
		json.setScale(0.3f);
		SkeletonData playerSkeletonData = json.readSkeletonData(Gdx.files.internal("spineboy.json"));
		System.out.println(playerSkeletonData.getHeight());
		AnimationStateData playerAnimationData = new AnimationStateData(playerSkeletonData);
		playerAnimationData.setMix("run", "jump", 0.2f);
		playerAnimationData.setMix("jump", "run", 0.2f);
		this.skeletonRenderer = new SkeletonRenderer();
		
		skeletonRenderer.setPremultipliedAlpha(true);
		
		this.skeleton = new Skeleton(playerSkeletonData);
		this.animationState = new AnimationState(playerAnimationData);
		
		this.current_animation = "idle";
		animationState.setAnimation(0, "idle", true); // trackIndex, name, loop
		
		
	}

	
	@Override
	public void draw(SpriteBatch batch, float delta) {
		// TODO Auto-generated method stub
		//super.draw(batch, delta);
		skeleton.setPosition(this.getX(), this.getY());
		skeletonRenderer.draw(batch, skeleton);
	}
	
	public void update(float delta, Vector2 gravity, ArrayList<Ladder> ladders){
		super.update(delta, gravity, canClimbDown);
		
		// CLIMB
		this.canClimbDown = this.isClimbingDown(ladders);
		this.canClimbUp = this.isClimbingUp(ladders);
		
		animationState.update(delta * 0.35f);
		animationState.apply(skeleton);
		skeleton.updateWorldTransform();
	}

	public void setAnimation(String animation, int index, boolean loop){
		if (!this.current_animation.equals(animation)){
			this.current_animation = animation;
			animationState.setAnimation(index, this.current_animation, loop); // trackIndex, name, loop
		}
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
			if (this.velocityY == 0)
				this.setAnimation("run", 0, true); // trackIndex, name, loop
			
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			this.moveLeft(Gdx.graphics.getDeltaTime());
			if (this.velocityY == 0)
				this.setAnimation("run", 0, true); // trackIndex, name, loop
			skeleton.getFlipX();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			this.jump();
			this.setAnimation("jump", 0, false); // trackIndex, name, loop
		}
	}

	private boolean isClimbingUp(ArrayList<Ladder> ladders){
		if (!Gdx.input.isKeyPressed(Input.Keys.Z))
			return false;
		for (Ladder l : ladders) {
			if (this.collideManager.getCenterBox().overlaps(l.climbZone)) {
				return true;
			}
		}
		return false;
	}

	private boolean isClimbingDown(ArrayList<Ladder> ladders){
		if (!Gdx.input.isKeyPressed(Input.Keys.S))
			return false;
		for (Ladder l : ladders) {
			if (this.collideManager.getCenterBox().overlaps(l.climbZoneDown)) {
				return true;
			}
		}
		return false;
	}

	public float getCenterX() {
		return this.collideManager.getCenterX();
	}

	public float getCenterY() {
		return this.collideManager.getCenterY();
	}
	
	@Override
	public void applyHorizontalCollidingEffect(CollideBox collider, Hitbox hitbox){
		switch (hitbox) {
		case CENTER:
			break;
		case LEFT:
			this.stopH(collider.getX() + collider.getWidth() + 1);
			break;
		case RIGHT:
			this.stopH(collider.getX() - this.getWidth() - 1);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void applyVerticalCollidingEffect(CollideBox collider, Hitbox hitbox){
		switch (hitbox) {
		case CENTER:
			break;
		case BOTTOM:
			if (!this.canClimbDown) {
				this.resetJump();
				this.stopV(collider.getY() + collider.getHeight());
			}
			break;
		case TOP:
			if (!this.canClimbUp && this.isMovingUp()) {
				this.stopV(collider.getY() - this.getHeight());
			}
			break;
		default:
			break;
		}
	}


}
