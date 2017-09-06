package com.elemens.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.elemensia.game.ElemensiaConfiguration;
import com.elemensia.game.ElemensiaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new ElemensiaConfiguration(800, 450, true);
		
		ElemensiaGame elemensia = new ElemensiaGame();
		new LwjglApplication(elemensia, config);
	}
}
