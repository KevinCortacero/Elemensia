package level_editor.view;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class LevelManager extends PanelManager{

	public LevelManager() {
		super("Level Management");
		this.setLayout(new GridBagLayout());
		this.add(new LevelManagerButton(new ImageIcon("assets/button_icon/new-icon.png")));
		this.add(new LevelManagerButton(new ImageIcon("assets/button_icon/open-icon.png")));
		this.add(new LevelManagerButton(new ImageIcon("assets/button_icon/save-icon.png")));
		this.add(new LevelManagerButton(new ImageIcon("assets/button_icon/play-icon.png")));
	}
}
