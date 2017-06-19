package com.elemens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ElemensiaGame extends Game{

	public static final boolean DEBUG = true;
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 450;

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		//ScreenManager.getInstance().showScreen(new WorldGameScreen());
		ScreenManager.getInstance().showScreen(new SplashScreen());
	}
	
	@Override
	public void render() {
		super.render();
		this.getScreen().render(Math.min(Gdx.graphics.getDeltaTime(), 0.035f));
	}
}
