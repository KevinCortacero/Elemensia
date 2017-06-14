package com.elemens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ElemensiaGame extends Game{

	private static final boolean DEBUG = true;
	private BitmapFont font;
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private OrthographicCamera camera;
	private World world;
	
	@Override
	public void create() {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 1600, 900);
		this.batch = new SpriteBatch();
		this.sr = new ShapeRenderer();
		this.font = new BitmapFont();
		Utility.loadTextureAsset("village_back.png");
		Utility.loadTextureAsset("village_front.png");
		Utility.loadTextureAsset(Twarzian.SPRITE_PATH);
		Utility.loadTextureAsset(Hero.HERO_SPRITE_PATH);
		this.world = World.create(0.0f, -9.8f);
	}
	
	@Override
	public void render() {
		super.render();
		this.camera.position.set(this.world.getCameraPosition(), 0);
		this.camera.update();
		
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		this.world.draw(this.batch, Gdx.graphics.getDeltaTime());
		this.batch.end();
		
		this.sr.begin(ShapeType.Line);
		this.sr.setProjectionMatrix(this.camera.combined);
		if(DEBUG)
			this.world.draw(sr);
		this.sr.end();
		
		this.world.update();
	}

	@Override
	public void dispose() {
		super.dispose();this.batch.dispose();
		this.world.dispose();
		Gdx.app.debug("ElemensGame", "dispose the game.");
		
	}
}
