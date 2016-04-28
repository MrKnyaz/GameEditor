package net.aknyazev.game.editor.ui;

import net.aknyazev.game.editor.assets.shaders.AbstractShader;
import net.aknyazev.game.editor.model.*;
import net.aknyazev.game.editor.util.FileUtils;
import net.aknyazev.game.editor.world.RenderData;

import java.util.ArrayList;

/**
 * Created by MrKnyaz on 2016-04-22.
 */
public class UIController {

    private RenderData renderData;
    private UIData uiData;
    private UI ui;

    public UIController(RenderData renderData,UIData uiData, UI ui) {
        this.renderData = renderData;
        this.uiData = uiData;
        this.ui = ui;
    }

    public void setDynamicItem(AbstractGameObject gameObject) {
        renderData.setDynamicItem(gameObject);
    }

    public void setCurrentLayer(int index) {
        renderData.setCurrentLayer(index);
        ui.updateLayerInfo();
    }

    public void rearrangeLayer(int direction) {
        ArrayList<Layer> layers = renderData.getLayers();
        int currentLayer = renderData.getCurrentLayer();
        if (currentLayer + direction < layers.size() && currentLayer + direction >= 0) {
            Layer tmpLayer = layers.get(currentLayer);
            layers.set(currentLayer, layers.get(currentLayer+direction));
            layers.set(currentLayer+direction, tmpLayer);
            renderData.setCurrentLayer(currentLayer+direction);
            ui.updateLayersList();
        }

    }

    public void saveOrUpdateLayer(String name, String speed, AbstractShader shader, boolean looped, Layer layer) {
        try {
            if (layer == null) {
                ArrayList<Layer> layers = renderData.getLayers();
                layer = new Layer(name);
                layers.add(layer);
                ui.updateLayersList();
            }
            layer.setName(name);
            layer.setSpeed(Float.parseFloat(speed));
            layer.setShader(shader);
        } catch(Exception e) {}
    }

}
