package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Toolbox extends JPanel{
	
	public Toolbox(int width, int height) {
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new GridLayout(1,3));
		this.add(new TileManager());
		this.add(new LevelManager());
		JPanel p3 = new JPanel();
		p3.setBackground(Color.YELLOW);
		this.add(p3);

	}
}
