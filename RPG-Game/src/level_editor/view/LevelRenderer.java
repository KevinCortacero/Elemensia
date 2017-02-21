package level_editor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class LevelRenderer extends Canvas{

	public LevelRenderer(int width, int height) {
		super();
		this.setSize(new Dimension(width, height));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.GRAY);
	}
}
