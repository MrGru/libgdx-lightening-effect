package com.gdx.lightening;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Lightning {
	boolean isComplete();
	void update();
	void draw(SpriteBatch batch);
}
