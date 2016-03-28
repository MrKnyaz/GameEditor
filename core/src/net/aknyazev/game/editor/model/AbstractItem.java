package net.aknyazev.game.editor.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Author: MrKnyaz
 * Date: 05.02.14
 */
public abstract class AbstractItem {
    protected float rotation;
    protected float posX, posY;

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public abstract void draw(SpriteBatch batch);

    public AbstractItem copy() {
        return null;
    }
}
