package com.elemens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Animation {

	public abstract void setAnimation(State state, boolean loop);

	public abstract void update(float x, float y, float delta, Direction direction);

	public abstract void draw(SpriteBatch batch);
	
	public abstract void draw(ShapeRenderer sr);

}
