package com.elemens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ElemensGame extends ApplicationAdapter {
	
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private OrthographicCamera camera;
	private World world;
	
	@Override
	public void create () {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 1600, 900);
		this.batch = new SpriteBatch();
		this.sr = new ShapeRenderer();
		this.world = new World(0.0f, -9.8f);
		Gdx.app.debug(this.getClass().getName(), "Elemens Game created");
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.camera.position.set(this.world.getCameraPosition(), 0);
		this.camera.update();
		
		this.batch.setProjectionMatrix(camera.combined);
		this.batch.begin();
		this.world.draw(this.batch);
		this.batch.end();
		this.sr.begin(ShapeType.Line);
		this.sr.setProjectionMatrix(camera.combined);
		this.world.draw(sr);
		this.sr.end();
		
		this.world.update();
	}
	
	@Override
	public void dispose () {
		this.batch.dispose();
	}
}
