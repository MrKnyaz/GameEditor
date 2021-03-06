package net.aknyazev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.aknyazev.game.editor.Constants;
import net.aknyazev.game.editor.MapRenderer;
import net.aknyazev.game.editor.assets.AssetManager;
import net.aknyazev.game.editor.world.RenderData;
import net.aknyazev.game.editor.ui.UI;

public class PlatformerMapEditor extends ApplicationAdapter {
	UI ui;
	MapRenderer mapRenderer;
	SpriteBatch batch;
	AssetManager assetManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = AssetManager.getInstance();
		RenderData renderData = new RenderData();
		ui = new UI(renderData);
		mapRenderer = new MapRenderer(renderData);
	}

	@Override
	public void render () {
		final float delta = Gdx.graphics.getRawDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		moveCamera(delta);
		//Constants.cam.translate(10f*delta,0);
		batch.setProjectionMatrix(Constants.cam.combined);
		batch.begin();
		mapRenderer.render(batch, delta);
		batch.end();
		ui.render(delta);
		//System.out.println(delta);
	}

	void moveCamera(float delta) {
		float translation = 10f*delta;
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			Constants.cam.translate(-translation, 0);
			Constants.cam.update();
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			Constants.cam.translate(0, translation);
			Constants.cam.update();
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			Constants.cam.translate(0, -translation);
			Constants.cam.update();
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			Constants.cam.translate(translation, 0);
			Constants.cam.update();
		}
	}
	@Override
	public void resize(int width, int height) {
		ui.resize(width, height);
		//cam = new OrthographicCamera(20.0f, 20.0f * height / width);
		System.out.println(Constants.getViewPortWidth()+" "+Constants.getViewPortHeight());
		Constants.cam = new OrthographicCamera(Constants.getViewPortWidth(), Constants.getViewPortHeight());
		Constants.cam.position.set(Constants.cam.viewportWidth/2f, Constants.cam.viewportHeight/2f, 0);
		Constants.cam.update();
		//cam.setToOrtho(true, 10, 10);

	}

	@Override
	public void pause() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void resume() {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void dispose() {
		ui.dispose();
		assetManager.dispose();
	}
}
