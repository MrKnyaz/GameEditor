package net.aknyazev.game.editor.model;

/**
 * Created by MrKnyaz on 2016-05-05.
 */
public class LightObject extends AbstractGameObject {

    public LightObject(String name) {
        super();
        this.name = name;
    }

    public LightObject(LightObject lightObject) {
        super(lightObject);
    }

    public float getLength() {
        return width*scaleX;
    }

    public String toString() {
        return name;
    }
}
