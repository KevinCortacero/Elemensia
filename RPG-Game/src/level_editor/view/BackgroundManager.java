package level_editor.view;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundManager extends JPanel{

	public BackgroundManager() {
		super(new GridBagLayout());
		this.add(new JPanel());
		this.add(new BackgroundChooserButton());
	}
}
