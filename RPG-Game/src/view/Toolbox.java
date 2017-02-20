package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import view.tile_manager.TileManager;

@SuppressWarnings("serial")
public class Toolbox extends JPanel{
	
	public Toolbox(int width, int height) {
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new GridLayout(1,3));
		this.add(new TileManager());
		this.add(new LevelManager());
		this.add(new GameObjectManagement());

	}
}
