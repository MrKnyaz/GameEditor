package net.aknyazev.game.editor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MrKnyaz
 * Date: 05.02.14
 */
public class Layer {
    float offsetCoefficient;
    List<AbstractItem> items;
    String name;
    public Layer(String name) {
        items = new ArrayList<AbstractItem>();
        this.name = name;
    }

    public List<AbstractItem> getItems() {
        return items;
    }
    public String toString() {
        return name;
    }
}
