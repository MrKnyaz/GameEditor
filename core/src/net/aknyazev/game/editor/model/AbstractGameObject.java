package net.aknyazev.game.editor.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Author: MrKnyaz
 * Date: 05.02.14
 */
public abstract class AbstractGameObject {

    private long id;
    protected float rotation;
    protected float posX, posY;
    protected float scaleX, scaleY;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractGameObject that = (AbstractGameObject) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public abstract void draw(SpriteBatch batch);

    public AbstractGameObject copy() {
        return null;
    }
}
