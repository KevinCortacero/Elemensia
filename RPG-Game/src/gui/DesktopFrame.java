package gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class DesktopFrame {
	
	private JFrame frame;
	
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 900;
	private static final String TITLE = "Level Editor (Alpha)";
	private static final String LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	
	public DesktopFrame() {
		try {
			UIManager.setLookAndFeel(LOOK_AND_FEEL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.frame = new JFrame(TITLE);
		this.frame.setSize(WIDTH, HEIGHT);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.setLayout(new FlowLayout());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.add(new LevelRenderer(WIDTH, (HEIGHT * 3) / 4) );
		this.frame.add(new Toolbox(WIDTH, (HEIGHT * 1) / 4));
		this.frame.setVisible(true);
	}
}
