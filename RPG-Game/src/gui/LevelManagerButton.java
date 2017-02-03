package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LevelManagerButton extends JButton implements ActionListener{

	public LevelManagerButton(Icon background) {
		super(background);
		this.setPreferredSize(new Dimension (80, 80));
		this.setBackground(null);
		this.setBorder(null);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Click !");
	}
}
