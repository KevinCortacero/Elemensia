package com.elemens.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.elemens.ElemensiaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		config.title = "Elemens";
		config.useGL30 = false;
		config.width = ElemensiaGame.WINDOW_WIDTH;
		config.height = ElemensiaGame.WINDOW_HEIGHT;
		config.resizable = false;
		ElemensiaGame elemensia = new ElemensiaGame();
		Application app = new LwjglApplication(elemensia, config);
		Gdx.app = app;
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.setLogLevel(Application.LOG_ERROR);
	}
}
