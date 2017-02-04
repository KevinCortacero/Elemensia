package gui;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundManager extends JPanel{

	public BackgroundManager() {
		super();
		JFileChooser fc = new JFileChooser("assets/");
		//this.add(fc);
		//fc.showOpenDialog(this);
	}
}
