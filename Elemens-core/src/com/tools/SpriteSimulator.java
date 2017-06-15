package com.tools;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class SpriteSimulator {
		
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sprite Simulator";
		config.useGL30 = false;
		config.width = 800;
		config.height = 800;
		SpriteSimulatorApp app = new SpriteSimulatorApp();
		new LwjglApplication(app, config);
	}
}
