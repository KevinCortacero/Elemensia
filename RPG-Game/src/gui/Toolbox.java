package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Toolbox extends JPanel{
	
	public Toolbox(int width, int height) {
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.RED);
	}
}
