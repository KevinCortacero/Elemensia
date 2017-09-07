package com.elemens.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.elemens.ElemensiaConfiguration;
import com.elemens.ElemensiaGame;
import com.elemens.SplashScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new ElemensiaConfiguration(SplashScreen.WINDOW_WIDTH, SplashScreen.WINDOW_HEIGHT, true);
		
		ElemensiaGame elemensia = new ElemensiaGame();
		new LwjglApplication(elemensia, config);
	}
}
