package gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelManager extends JPanel{

	public PanelManager(String title) {
		super();
		TitledBorder tb = BorderFactory.createTitledBorder(title);
		tb.setTitleJustification(TitledBorder.TOP);
		this.setBorder(tb);
	}
}
