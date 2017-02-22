package level_editor.view;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LevelSizeManager extends PanelManager{

	public LevelSizeManager() {
		super("Size");
		this.add(new JLabel("Width :"));
		this.add(new JFormattedTextField());
		this.add(new JLabel("Height :"));
		this.add(new JFormattedTextField());
	}

}
