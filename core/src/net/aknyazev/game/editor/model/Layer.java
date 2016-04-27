package net.aknyazev.game.editor.model;

import net.aknyazev.game.editor.assets.AssetManager;
import net.aknyazev.game.editor.assets.shaders.AbstractShader;
import net.aknyazev.game.editor.datastructures.GameObjectTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MrKnyaz
 * Date: 05.02.14
 */
public class Layer {
    AbstractShader shader;
    //1 - normal speed, 0 - doesn't move
    float speed = 1.0f;
    GameObjectTree<AbstractGameObject> items;
    String name;
    public Layer(String name) {
        items = new GameObjectTree<AbstractGameObject>();
        this.name = name;
        shader = AssetManager.getInstance().getDefaultShader();
    }

    public List<AbstractGameObject> getItems() {
        return items.getAsList();
    }

    public void addItem(AbstractGameObject item) {
        items.add(item);
    }

    public void removeItem(AbstractGameObject item) {
        items.remove(item);
    }
    public String toString() {
        return name;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public AbstractShader getShader() {
        return shader;
    }

    public void setShader(AbstractShader shader) {
        this.shader = shader;
    }
}
