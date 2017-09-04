package com.elemensia;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class ElemensiaConfiguration extends LwjglApplicationConfiguration{

	public ElemensiaConfiguration(int width, int height, boolean undecorated) {
		this.width = width;
		this.height = height;
		this.fullscreen = false;
		this.resizable = false;
		this.samples = 3;
		this.useGL30 = false;
		System.setProperty("org.lwjgl.opengl.Window.undecorated", String.valueOf(undecorated));
	}
}
