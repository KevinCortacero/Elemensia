package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class GameObjectManagement extends PanelManager {

	public GameObjectManagement() {
		super("GameObject Management");
		this.setLayout(new GridLayout(1, 2));
		JTabbedPane tabs = new JTabbedPane();
		tabs.add("Background", new BackgroundManager());
		tabs.add("Foreground", new BackgroundManager());
		this.add(tabs);
		
		JPanel gamesObjects = new JPanel(new GridLayout(2,1));
		gamesObjects.setBackground(Color.BLACK);
		/*
		gamesObjects.add(new JPanel());
		gamesObjects.add(new JPanel());
		*/
		this.add(gamesObjects);
	}

}
