package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Toolbox extends JPanel{
	
	public Toolbox(int width, int height) {
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new GridLayout(1,3));
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.BLUE);
		JPanel p2 = new JPanel();
		JButton b1 = new JButton("new");
		b1.setPreferredSize(new Dimension (50, 50));
		p2.add(b1, BorderLayout.CENTER);
		JButton b2 = new JButton("open");
		p2.add(b2);
		//p2.add(new JButton("save"));
		p2.add(new JButton("play"));
		p2.setBackground(Color.CYAN);
		JPanel p3 = new JPanel();
		p3.setBackground(Color.YELLOW);
		this.add(panel1);
		this.add(p2);
		this.add(p3);

	}
}
