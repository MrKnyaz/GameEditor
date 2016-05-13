package net.aknyazev.game.editor.model;

/**
 * Created by MrKnyaz on 2016-05-05.
 */
public class LightObject extends AbstractGameObject {

    String name;
    public LightObject(String name) {
        super();
        this.name = name;
    }

    public LightObject(LightObject lightObject) {
        super(lightObject);
        this.name = lightObject.name;
    }

    public float getLength() {
        return width*scaleX;
    }

    public String toString() {
        return name;
    }
}
