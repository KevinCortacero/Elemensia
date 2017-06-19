package com.elemens.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.elemens.ElemensiaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 0;
		config.height = 0;
		config.fullscreen = false;
		config.resizable = false;
		ElemensiaGame elemensia = new ElemensiaGame();
		new LwjglApplication(elemensia, config);
	}
}
