package com.elemensia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class AbstractScreen extends Stage implements Screen {

	protected AbstractScreen(float width, float height) {
		super(new StretchViewport(width, height, new OrthographicCamera()));
	}

	// Subclasses must load actors in this method
	public abstract void buildStage();

	@Override
	public void render(float delta) {
		// Clear screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT
				| (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

		// Calling to Stage methods
		super.act(delta);
		super.draw();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		this.getViewport().update(width, height, true);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}
