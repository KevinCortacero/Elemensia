package level_editor.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class BackgroundChooserButton extends JButton implements ActionListener{
	
	public BackgroundChooserButton() {
		super("add...");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser("assets/backgrounds");
		fc.showOpenDialog(this);
	}
}
