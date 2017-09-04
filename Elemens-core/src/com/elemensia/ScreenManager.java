package com.elemensia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class ScreenManager {

	private static ScreenManager instance;
	
	private ElemensiaGame game;
	
	private ScreenManager(){};
	
	public static ScreenManager getInstance(){
		if (instance == null)
			instance = new ScreenManager();
		return instance;
	}
	
	public void initialize(ElemensiaGame game){
		this.game = game;
	}
	
	public void showScreen(AbstractScreen newScreen, boolean undecorated) {
		 
        // Get current screen to dispose it
        Screen currentScreen = this.game.getScreen();
 
        // Show new screen
        newScreen.buildStage();
        this.game.setScreen(newScreen);
        Gdx.graphics.setUndecorated(undecorated);
		Gdx.graphics.setWindowedMode((int) newScreen.getWidth(), (int) newScreen.getHeight());
 
        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
