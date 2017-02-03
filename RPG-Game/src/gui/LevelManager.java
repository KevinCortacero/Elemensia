package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class LevelManager extends PanelManager{

	public LevelManager() {
		super("Level Management");
		
		JButton b1 = new JButton("new");
		b1.setPreferredSize(new Dimension (80, 80));
		this.add(b1, BorderLayout.CENTER);
		
		JButton b2 = new JButton("open");
		b2.setPreferredSize(new Dimension (80, 80));
		this.add(b2, BorderLayout.CENTER);
		
		JButton b3 = new JButton("save");
		b3.setPreferredSize(new Dimension (80, 80));
		this.add(b3, BorderLayout.CENTER);
		
		JButton b4 = new JButton("play");
		b4.setPreferredSize(new Dimension (80, 80));
		this.add(b4, BorderLayout.CENTER);
		
		//this.setBackground(Color.CYAN);
	}
}
