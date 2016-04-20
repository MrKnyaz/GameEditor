package net.aknyazev.game.editor.world;


import com.badlogic.gdx.graphics.OrthographicCamera;
import net.aknyazev.game.editor.Constants;
import net.aknyazev.game.editor.model.AbstractGameObject;
import net.aknyazev.game.editor.model.Layer;
import net.aknyazev.game.editor.ui.command.AddCommand;
import net.aknyazev.game.editor.ui.command.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MrKnyaz
 * Date: 23.12.13
 */
public class RenderData {

    public long size;

    private ArrayList<Layer> layers;
    private boolean allLayers = true;
    private int currentLayer;
    private AbstractGameObject dynamicItem;

    public RenderData() {
        layers = new ArrayList<Layer>();
        layers.add(new Layer("layer 1"));
    }

    public boolean isAllLayers() {
        return allLayers;
    }

    public void setAllLayers(boolean allLayers) {
        this.allLayers = allLayers;
    }

    public int getCurrentLayer() {
        return currentLayer;
    }

    public void setCurrentLayer(int currentLayer) {
        this.currentLayer = currentLayer;
    }

    public AbstractGameObject getDynamicItem() {
        return dynamicItem;
    }

    public void setDynamicItem(AbstractGameObject dynamicItem) {
        this.dynamicItem = dynamicItem;
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public Command addItem(AbstractGameObject item) {
        item.setId(size++);
        return new AddCommand(item, layers.get(currentLayer)).execute();
    }

    public float getWorldX(int screenX) {
        OrthographicCamera camera = Constants.cam;
        //because of stupid middle-screen coordinates I moved camera by half of viewport at the beginning
        //So we apply speed to layer only after we subtract this half
        //This is done to have layers at the same position on start
        return (camera.position.x-camera.viewportWidth/2)*layers.get(currentLayer).getSpeed()
                +screenX / Constants.getPixelsPerUnit();
    }

    public float getWorldY(int screenY) {
        OrthographicCamera camera = Constants.cam;
        return (camera.position.y-camera.viewportHeight/2)*layers.get(currentLayer).getSpeed()
                +(Constants.getViewPortHeight()-screenY/Constants.getPixelsPerUnit());
    }
}
