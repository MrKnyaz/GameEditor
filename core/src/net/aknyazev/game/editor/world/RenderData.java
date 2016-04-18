package net.aknyazev.game.editor.world;


import net.aknyazev.game.editor.model.AbstractGameObject;
import net.aknyazev.game.editor.model.Layer;
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

    private class AddCommand extends Command {

        AbstractGameObject item;
        Layer layer;

        public AddCommand(AbstractGameObject item, Layer layer) {
            this.item = item;
            this.layer = layer;
        }

        @Override
        public void runCommand() {
            layer.addItem(item);
        }

        @Override
        public void undoCommand() {
            layer.removeItem(item);
        }

        @Override
        public void redoCommand() {
            runCommand();
        }
    }
}
