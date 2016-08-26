package net.aknyazev.game.editor.world;


import com.badlogic.gdx.graphics.OrthographicCamera;
import net.aknyazev.game.editor.Constants;
import net.aknyazev.game.editor.model.AbstractGameObject;
import net.aknyazev.game.editor.model.Layer;
import net.aknyazev.game.editor.ui.command.AddCommand;
import net.aknyazev.game.editor.ui.command.Command;

import java.util.ArrayList;

/**
 * Author: MrKnyaz
 * Date: 23.12.13
 */
public class RenderData {

    public long size;

    private ArrayList<Layer> layers;
    private boolean allLayers = true;
    private int currentLayerIndex;
    private Layer currentLayer;
    private AbstractGameObject dynamicItem;

    //dark_intensity for lights
    private float darkIntensity = 95f;
    private boolean addDarkIntensity = true;

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

    public int getCurrentLayerIndex() {
        return currentLayerIndex;
    }

    public void setCurrentLayerIndex(int currentLayerIndex) {
        this.currentLayerIndex = currentLayerIndex;
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

    public float getDarkIntensity() {
        return darkIntensity;
    }

    public void setDarkIntensity(float darkIntensity) {
        if (addDarkIntensity) {
            if (this.darkIntensity+darkIntensity < 95) {
                this.darkIntensity += darkIntensity;
            } else {
                addDarkIntensity =false;
            }
        } else if (!addDarkIntensity){
            if (this.darkIntensity - darkIntensity > 95) {
                this.darkIntensity -= darkIntensity;
            } else {
                addDarkIntensity = true;
            }
        }
    }

    public Layer getCurrentLayer() {
        return currentLayer;
    }

    public void setCurrentLayer(Layer currentLayer) {
        this.currentLayer = currentLayer;
    }

    public Command addItem(AbstractGameObject item) {
        item.setId(size++);
        return new AddCommand(item, layers.get(currentLayerIndex)).execute();
    }

    public float getWorldX(int screenX) {
        OrthographicCamera camera = Constants.cam;
        //because of stupid middle-screen coordinates I moved camera by half of viewport at the beginning
        //So we apply speed to layer only after we subtract this half
        //This is done to have layers at the same position on start
        return (camera.position.x-camera.viewportWidth/2)*layers.get(currentLayerIndex).getSpeed()
                +screenX / Constants.getPixelsPerUnit();
    }

    public float getWorldY(int screenY) {
        OrthographicCamera camera = Constants.cam;
        return (camera.position.y-camera.viewportHeight/2)*layers.get(currentLayerIndex).getSpeed()
                +(Constants.getViewPortHeight()-screenY/Constants.getPixelsPerUnit());
    }

}
