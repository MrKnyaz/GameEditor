package net.aknyazev.game.editor.world;


import com.badlogic.gdx.graphics.OrthographicCamera;
import net.aknyazev.game.editor.Constants;
import net.aknyazev.game.editor.model.AbstractGameObject;
import net.aknyazev.game.editor.model.Layer;
import net.aknyazev.game.editor.ui.command.AddCommand;
import net.aknyazev.game.editor.ui.command.Command;

/**
 * Author: MrKnyaz
 * Date: 23.12.13
 */
public class RenderData {

    public long size;

    private Layer[] layers;
    private boolean allLayers = true;
    private int currentLayer;
    private AbstractGameObject dynamicItem;

    public RenderData() {
        layers = new Layer[2];
        layers[0] = new Layer("testLayer");
        layers[1] = new Layer("testLayer1");
        layers[0].setSpeed(0.2f);
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

    public Layer[] getLayers() {

        return layers;
    }

    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    public Command addItem(AbstractGameObject item) {
        item.setId(size++);
        return new AddCommand(item, layers[currentLayer]).execute();
    }

    public float getWorldX(int screenX) {
        OrthographicCamera camera = Constants.cam;
        return (camera.position.x-camera.viewportWidth/2)*layers[currentLayer].getSpeed()
                +screenX / Constants.getPixelsPerUnit();
    }

    public float getWorldY(int screenY) {
        OrthographicCamera camera = Constants.cam;
        return (camera.position.y-camera.viewportHeight/2)*layers[currentLayer].getSpeed()
                +(Constants.getViewPortHeight()-screenY/Constants.getPixelsPerUnit());
    }
}
