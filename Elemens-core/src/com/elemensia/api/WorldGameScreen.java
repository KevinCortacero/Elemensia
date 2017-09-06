package com.elemensia.api;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.elemensia.game.ElemensiaGame;
import com.elemensia.game.World;

public class WorldGameScreen extends AbstractScreen {

	public WorldGameScreen() {
		super(1600, 900);
	}
	
	//private BitmapFont font;
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private OrthographicCamera camera;
	private OrthographicCamera staticCamera;
	private World world;
	
	@Override
	public void show() {
		super.show();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, this.getWidth(), this.getHeight());
		this.staticCamera = new OrthographicCamera();
		this.staticCamera.setToOrtho(false, this.getWidth(), this.getHeight());
		this.batch = new SpriteBatch();
		this.sr = new ShapeRenderer();
		//this.font = new BitmapFont();
		Utility.loadTextureAsset("living_things/npc/npc.png");
		Utility.loadTextureAsset("living_things/creatures/cube/cube.png");
		Utility.loadTextureAsset("world/bg.jpg");
		Utility.loadTextureAsset("apple.png");
		this.world = World.create(0.0f, -9.8f);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		//this.camera.zoom += delta*0.2;
		this.camera.position.set(this.world.getCameraPosition(this.getWidth()/2, this.getHeight()/2), 0);
		this.camera.update();
		
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		this.world.draw(this.batch, delta);
		this.batch.end();
		
		this.sr.begin(ShapeType.Line);
		this.sr.setProjectionMatrix(this.camera.combined);
		if(ElemensiaGame.DEBUG)
			this.world.draw(sr);
		this.sr.end();
		
		/*
		this.sr.setProjectionMatrix(this.staticCamera.combined);
		this.sr.begin(ShapeType.Filled);
		this.world.drawEnvironement(sr);
		this.sr.end();
		*/
		this.world.update(delta);
	}

	@Override
	public void buildStage() {
		// TODO Auto-generated method stub
		
	}

}
