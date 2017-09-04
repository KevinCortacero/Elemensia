package com.elemens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ElemensiaGame extends Game{

	public static final boolean DEBUG = false;
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 450;

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(new WorldGameScreen(), false);
		//ScreenManager.getInstance().showScreen(new SplashScreen(), true);
	}
	
	@Override
	public void render() {
		super.render();
		this.getScreen().render(Gdx.graphics.getDeltaTime());
	}
}
