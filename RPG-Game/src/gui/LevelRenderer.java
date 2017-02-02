package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class LevelRenderer extends Canvas{

	public LevelRenderer(int width, int height) {
		super();
		this.setSize(new Dimension(width, height));
		this.setBackground(Color.GRAY);
	}
}
