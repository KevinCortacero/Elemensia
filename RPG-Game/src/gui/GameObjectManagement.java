package gui;

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
		
		JPanel gamesObjects = new JPanel(new GridLayout(3,1));
		
		gamesObjects.add(new SolidBlockManager());
		gamesObjects.add(new LevelGravityManager());
		gamesObjects.add(new LevelSizeManager());
		
		this.add(gamesObjects);
	}

}
