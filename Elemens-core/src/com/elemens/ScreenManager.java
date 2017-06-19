package com.elemens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenManager {

	private static ScreenManager instance;
	
	private Game game;
	
	private ScreenManager(){};
	
	public static ScreenManager getInstance(){
		if (instance == null)
			instance = new ScreenManager();
		return instance;
	}
	
	public void initialize(Game game){
		this.game = game;
	}
	
	public void showScreen(AbstractScreen newScreen) {
		 
        // Get current screen to dispose it
        Screen currentScreen = this.game.getScreen();
 
        // Show new screen
        this.game.setScreen(newScreen);
 
        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
