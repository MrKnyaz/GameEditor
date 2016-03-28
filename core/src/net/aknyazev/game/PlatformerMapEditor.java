package net.aknyazev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.aknyazev.game.editor.Constants;
import net.aknyazev.game.editor.MapRenderer;
import net.aknyazev.game.editor.model.RenderData;
import net.aknyazev.game.editor.ui.UI;

public class PlatformerMapEditor extends ApplicationAdapter {
	UI ui;
	MapRenderer mapRenderer;
	SpriteBatch batch;
	OrthographicCamera cam;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		RenderData renderData = new RenderData();
		ui = new UI(renderData);
		mapRenderer = new MapRenderer(renderData);
	}

	@Override
	public void render () {
		final float delta = Gdx.graphics.getRawDeltaTime();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		ui.render(delta);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		mapRenderer.render(batch, delta);
		batch.end();
	}
	@Override
	public void resize(int width, int height) {
		ui.resize(width, height);
		//cam = new OrthographicCamera(20.0f, 20.0f * height / width);
		System.out.println(Constants.getViewPortWidth()+" "+Constants.getViewPortHeight());
		cam = new OrthographicCamera(Constants.getViewPortWidth(), Constants.getViewPortHeight());
		cam.position.set(cam.viewportWidth/2f, cam.viewportHeight/2f, 0);
		cam.update();
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
	}
}
