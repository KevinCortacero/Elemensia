package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class GameObjectManagement extends PanelManager {

	public GameObjectManagement() {
		super("GameObject Management");
		this.setLayout(new GridLayout(1, 2));

		JPanel bgs = new JPanel(new GridLayout(1, 1));
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
		tabs.add("Background", null);
		tabs.add("Foreground", null);
		bgs.add(tabs, BorderLayout.CENTER);
		this.add(bgs, BorderLayout.WEST);
		
		JPanel gamesObjects = new JPanel(new GridLayout(2,1));
		gamesObjects.setBackground(Color.BLACK);
		/*
		gamesObjects.add(new JPanel());
		gamesObjects.add(new JPanel());
		*/
		this.add(gamesObjects, BorderLayout.EAST);
		
		
		
	}

}
