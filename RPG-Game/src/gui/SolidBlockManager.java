package gui;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SolidBlockManager extends PanelManager{

	public SolidBlockManager() {
		super("Solid Blocks");
		this.add(new JButton("sqr"));
		this.add(new JButton("trgl"));
		this.add(new JButton("del"));
	}
}
