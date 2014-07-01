package com.gdx.lightening;

import java.util.Vector;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class LighteningTest extends ApplicationAdapter {


	SpriteBatch batch;
	Vector<LightningBolt> bolts = new Vector<LightningBolt>();
	private OrthographicCamera camera = null;
	public LighteningTest(){
	}

	@Override
	public void create () {
		// Create assets manager
		AssetManager assetManager = new AssetManager();
		// create a new sprite batch to render the graphics
		batch = new SpriteBatch();
		Art.load(assetManager);
		assetManager.finishLoading();
		Art.assignResource(assetManager);

		// Ortographic camera
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render () {


		if(Gdx.input.justTouched() ) {
			bolts.add(new LightningBolt(new Vector2( Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2), 
					new Vector2(Gdx.input.getX(), Gdx.input.getY())));
		}
		
		
		// draw the Scene
		updateBolts();
		drawScene();
		removeCompleted();
	}

	private void updateBolts(){
		for(int i=0; i<bolts.size(); i++){
			bolts.get(i).update();
		}
	}
	private void removeCompleted() {
		int count = bolts.size();
		for(int i=0; i<count; i++){
			if (bolts.get(i).isComplete()){
				bolts.remove(i);
				count--;
			}
		}
	}

	void drawScene(){

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        camera.update();
        
        // Start rendering
        batch.begin();
        
        batch.setProjectionMatrix(camera.combined);

		
		for(int i=0; i<bolts.size(); i++){
			bolts.get(i).draw(batch);
		}
		batch.end();
		return;
	}

}
