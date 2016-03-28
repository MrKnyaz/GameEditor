package net.aknyazev.game.editor.model;


/**
 * Author: MrKnyaz
 * Date: 23.12.13
 */
public class RenderData {

    private Layer[] layers;
    private boolean allLayers;
    private int currentLayer;
    private AbstractItem dynamicItem;

    public RenderData() {
        layers = new Layer[1];
        layers[0] = new Layer("testLayer");
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

    public AbstractItem getDynamicItem() {
        return dynamicItem;
    }

    public void setDynamicItem(AbstractItem dynamicItem) {
        this.dynamicItem = dynamicItem;
    }

    public Layer[] getLayers() {

        return layers;
    }

    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }
}
