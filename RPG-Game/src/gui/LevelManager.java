package gui;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class LevelManager extends PanelManager{

	public LevelManager() {
		super("Level Management");
		this.add(new LevelManagerButton(new ImageIcon()));
		this.add(new LevelManagerButton(new ImageIcon()));
		this.add(new LevelManagerButton(new ImageIcon()));
		this.add(new LevelManagerButton(new ImageIcon()));
	}
}
