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
    protected float scaleX = 1f, scaleY = 1f;
    protected float opacity = 1f;
    protected boolean flipX;
    protected boolean flipY;
    protected float width = 1, height = 1;

    public AbstractGameObject() {}
    public AbstractGameObject(AbstractGameObject gameObject) {
        this.rotation = gameObject.rotation;
        this.posX = gameObject.posX;
        this.posY = gameObject.posY;
        this.scaleX = gameObject.scaleX;
        this.scaleY = gameObject.scaleY;
        this.opacity = gameObject.opacity;
        this.flipX = gameObject.flipX;
        this.flipY = gameObject.flipY;
        this.width = gameObject.width;
        this.height = gameObject.height;
    }

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

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        if (opacity > 1)  {
            opacity = 1;
        }
        if (opacity < 0)  {
            opacity = 0;
        }
        this.opacity = opacity;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    public float getXLow() {
        return posX-width*scaleX/2;
    }
    public float getXHigh() {
        return posX+width*scaleX/2;
    }
    public float getYLow() {
        return posY-height*scaleY/2;
    }
    public float getYHigh() {
        return posY+height*scaleY/2;
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractGameObject that = (AbstractGameObject) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }*/

    public void draw(SpriteBatch batch, float delta){}

    @Override
    public String toString() {
        return posX+" "+posY;
    }
}
