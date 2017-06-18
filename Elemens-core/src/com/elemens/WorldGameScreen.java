package com.elemens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WorldGameScreen extends AbstractScreen {

	private BitmapFont font;
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private OrthographicCamera camera;
	private World world;
	
	@Override
	public void show() {
		super.show();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, ElemensiaGame.WINDOW_WIDTH, ElemensiaGame.WINDOW_HEIGHT);
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
	public void render(float delta) {
		super.render(delta);
		this.camera.position.set(this.world.getCameraPosition(), 0);
		this.camera.update();
		
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		this.world.draw(this.batch, Gdx.graphics.getDeltaTime());
		this.batch.end();
		
		this.sr.begin(ShapeType.Line);
		this.sr.setProjectionMatrix(this.camera.combined);
		if(ElemensiaGame.DEBUG)
			this.world.draw(sr);
		this.sr.end();
		
		this.world.update(delta);
	}

	@Override
	public void buildStage() {
		// TODO Auto-generated method stub
		
	}

}
