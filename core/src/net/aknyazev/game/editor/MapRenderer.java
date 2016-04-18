package net.aknyazev.game.editor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        Layer[] layers = renderData.getLayers();
        for (int i = 0; i < layers.length; i++) {
            Layer layer = layers[i];
            List<AbstractGameObject> items = layer.getItems();
            for (int j = 0; j < items.size(); j++) {
                AbstractGameObject abstractItem =  items.get(j);
                abstractItem.draw(batch);
            }
            if (renderData.getCurrentLayer() == i) {
                AbstractGameObject dynamicItem = renderData.getDynamicItem();
                if (dynamicItem != null) {
                    dynamicItem.draw(batch);
                }
            }
        }

    }
}
