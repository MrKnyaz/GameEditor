package net.aknyazev.game.editor.model;

import net.aknyazev.game.editor.datastructures.GameObjectTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MrKnyaz
 * Date: 05.02.14
 */
public class Layer {
    float offsetCoefficient;
    GameObjectTree<AbstractGameObject> items;
    String name;
    public Layer(String name) {
        items = new GameObjectTree<AbstractGameObject>();
        this.name = name;
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
}
