package net.aknyazev.game.editor.datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by MrKnyaz on 2016-04-15.
 */
public class GameObjectTree<T> {
    //TODO - proper quad tree
    List<T> list;
    public GameObjectTree() {
        list = new ArrayList<T>();
    }

    public void add(T item) {
        list.add(item);
    }

    public void remove(T item) {
        list.remove(item);
    }

    public List<T> getAsList() {
        return list;
    }
}
