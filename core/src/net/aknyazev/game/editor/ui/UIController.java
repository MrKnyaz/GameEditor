package net.aknyazev.game.editor.ui;

import com.badlogic.gdx.Gdx;
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

    public void loadGraphics(String path) {
        uiData.setAtlases(FileUtils.loadFromPack(path));
        ui.updateAtlases();
        setCurrentAtlas(uiData.getAtlases()[0]);
        //update atlases
        renderData.setDynamicItem(null);
    }

    public void setCurrentAtlas(Atlas atlas) {
        uiData.setCurrentAtlas(atlas);
        ui.updateSpritesList();
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

    public void addNewLayer(Layer layer) {
        ArrayList<Layer> layers = renderData.getLayers();
        layers.add(layer);
        ui.updateLayersList();
    }

}
