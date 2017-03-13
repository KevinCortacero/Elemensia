package level_editor.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

@SuppressWarnings("serial")
public class LevelRenderer extends Canvas{
	
	private TmxMapLoader levelReader;
	private ExternalFileHandleResolver assets;

	public LevelRenderer(int width, int height) {
		super();
		this.setSize(new Dimension(width, height));
		TiledMap map = new TmxMapLoader(new ExternalFileHandleResolver()).load("assets/levels/town.tmx");
		/*this.assets = new ExternalFileHandleResolver();
		this.levelReader = new TmxMapLoader(this.assets);
		this.levelReader.load("levels/town.tmx");*/
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.GRAY);
	}
}
