package view;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LevelGravityManager extends PanelManager{

	public LevelGravityManager() {
		super("Gravity");
		this.add(new JLabel("Gravity X :"));
		this.add(new JFormattedTextField());
		this.add(new JLabel("Gravity Y :"));
		this.add(new JFormattedTextField());
	}

}
