package net.aknyazev.game.editor.model;

/**
 * Created by MrKnyaz on 2016-05-05.
 */
public class LightObject extends AbstractGameObject {

    boolean isAnimated = false;
    float radius = 2;
    float intensity = 100;

    public LightObject(boolean isAnimated) {
        super();
        this.isAnimated = isAnimated;
    }

    public LightObject(LightObject lightObject) {
        super(lightObject);
        this.isAnimated = lightObject.isAnimated;
    }

    public float getRadius() {
        return radius*scaleX;
    }

}
