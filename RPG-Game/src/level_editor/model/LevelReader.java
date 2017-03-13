package level_editor.model;

import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class LevelReader extends TmxMapLoader{

	public LevelReader(ExternalFileHandleResolver assets) {
		super(assets);
	}
	
	public TiledMap loadMap(String mapPath){
		return this.load(mapPath);
	}
	
}
