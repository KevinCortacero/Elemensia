package com.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.elemensia.api.Utility;

public class SpriteSimulatorApp extends Game{

	private static final String SPRITE_FOLDER = SpriteGenerator.SPRITE_FOLDER;
	private static final int NB_FRAMES = 8;
	private Map<String, Animation<TextureRegion>> animations;
	private List<String> keys;
	private int count;
	private float timer;
	private BitmapFont font;
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private OrthographicCamera camera;

	@Override
	public void create() {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, 800, 800);
		this.batch = new SpriteBatch();
		this.sr = new ShapeRenderer();
		this.font = new BitmapFont();
		final File spriteFolder = new File(SPRITE_FOLDER);
		this.animations = new HashMap<String, Animation<TextureRegion>>();
		this.keys = new ArrayList<String>();
		this.count = 0;
		this.reset();
		for(String filename : spriteFolder.list()){
			Utility.loadTextureAsset(SPRITE_FOLDER + filename);
			Texture sprite = Utility.getTextureAsset(SPRITE_FOLDER + filename);
			TextureRegion[][] tmp = TextureRegion.split(sprite, sprite.getWidth()/NB_FRAMES, sprite.getHeight());
			TextureRegion[] animation = new TextureRegion[NB_FRAMES];
			for (int i = 0; i < tmp[0].length; i++) {
				animation[i] = tmp[0][i];
			}
			this.animations.put(filename, new Animation<TextureRegion>(0.15f, animation));
			this.keys.add(filename);
		}
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClearColor( 1, 1, 1, 1 );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		this.update(Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_DOWN))
			this.down();
		else if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP))
			this.up();
		this.sr.begin(ShapeType.Filled);
		this.sr.rect(0, 0, 800, 200, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY);
		this.sr.end();
		this.batch.begin();
		this.batch.draw(this.getCurrentAnimation(), 200, 200);
		this.font.draw(this.batch, this.keys.get(this.count), 200, 100);
		this.batch.end();
		
	}

	public TextureRegion getCurrentAnimation() {
		String key = this.keys.get(this.count);
		return this.animations.get(key).getKeyFrame(this.timer, true);
	}

	public void update(float delta){
		this.timer += delta;
	}

	public void reset(){
		this.timer = 0;
	}

	public void up(){
		if (this.count < this.keys.size() -1)
			this.count++;
		else
			this.count = 0;
		this.reset();
	}

	public void down(){
		if (this.count > 0)
			this.count--;
		else
			this.count = this.keys.size() -1;
		this.reset();
	}
}
