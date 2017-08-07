package com.elemens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ElemensiaGame extends Game{

	public static final boolean DEBUG = true;


	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		//ScreenManager.getInstance().showScreen(new WorldGameScreen(), false);
		ScreenManager.getInstance().showScreen(new SplashScreen(), true);
	}
	
	@Override
	public void render() {
		super.render();
		this.getScreen().render(Math.min(Gdx.graphics.getDeltaTime(), 0.016f));
	}
}
