package net.aknyazev.game.editor;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.aknyazev.game.editor.assets.AssetManager;
import net.aknyazev.game.editor.assets.shaders.AbstractShader;
import net.aknyazev.game.editor.assets.shaders.LightShader;
import net.aknyazev.game.editor.model.AbstractGameObject;
import net.aknyazev.game.editor.model.Layer;
import net.aknyazev.game.editor.world.RenderData;

import java.util.List;

/**
 * Author: MrKnyaz
 * Date: 05.02.14
 */
public class MapRenderer {

    private RenderData renderData;
    public MapRenderer(RenderData renderData) {
        this.renderData = renderData;
    }

    public void render(SpriteBatch batch, float delta) {
        List<Layer> layers = renderData.getLayers();
        OrthographicCamera cam = Constants.cam;
        float camStandartPosX = cam.position.x;
        float camStandartPosY = cam.position.y;

        AssetManager assetManager = AssetManager.getInstance();
        AbstractShader lastShader = assetManager.getDefaultShader();
        assetManager.getDefaultShader().apply(batch, null);

        for (int i = layers.size()-1; i >= 0; i--) {
            Layer layer = layers.get(i);
            //change OpenGL projection matrix according to layer speed
            cam.position.x = Constants.getViewPortWidth()/2+(camStandartPosX-Constants.getViewPortWidth()/2)*layer.getSpeed();
            cam.position.y = Constants.getViewPortHeight()/2+(camStandartPosY-Constants.getViewPortHeight()/2)*layer.getSpeed();
            cam.update();
            batch.setProjectionMatrix(cam.combined);

            //search the tree and save found items
            layer.prepareData(cam.position.x - Constants.getViewPortWidth()/2, cam.position.y - Constants.getViewPortHeight()/2,
                    cam.position.x + Constants.getViewPortWidth()/2, cam.position.y + Constants.getViewPortHeight()/2);
            AbstractShader layerShader = layer.getShader();
            if (!lastShader.equals(layerShader) || layerShader instanceof LightShader) {
                layerShader.apply(batch, layer);
                lastShader = layerShader;
            }
            List<AbstractGameObject> items = layer.getItems();
            for (int j = 0; j < items.size(); j++) {
                AbstractGameObject abstractItem =  items.get(j);
                abstractItem.draw(batch);
            }

            //render item bound to mouse
            if (renderData.getCurrentLayer() == i) {
                AbstractGameObject dynamicItem = renderData.getDynamicItem();
                if (dynamicItem != null) {
                    dynamicItem.draw(batch);
                }
            }
        }
        cam.position.x = camStandartPosX;
        cam.position.y = camStandartPosY;
        cam.update();

    }
}
