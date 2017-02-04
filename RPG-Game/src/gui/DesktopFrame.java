package gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DesktopFrame {
	
	private JFrame frame;
	
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 900;
	private static final String TITLE = "Level Editor (Alpha)";
	
	public DesktopFrame() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.frame = new JFrame(TITLE);
		this.frame.setSize(WIDTH, HEIGHT);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.setLayout(new FlowLayout());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.add(new LevelRenderer(WIDTH, (HEIGHT * 3) / 4));
		this.frame.add(new Toolbox(WIDTH, (HEIGHT * 1) / 4));
		this.frame.setVisible(true);
	}
}
