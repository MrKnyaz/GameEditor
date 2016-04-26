package net.aknyazev.game.editor.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Author: MrKnyaz
 * Date: 22.12.13
 */
public class Atlas {

    private String name;
    private Texture texture;
    private SpriteObject[] spriteObjects;
    private SpriteObject currentSprite;

    public Atlas(String name, Texture texture, TextureRegion[] regions) {
        this.name = name;
        this.texture = texture;
        spriteObjects = new SpriteObject[regions.length];
        for (int i = 0; i < regions.length; i++) {
            TextureRegion region = regions[i];
            //spriteObjects[i] = new SpriteObject(region);
        }
        currentSprite = spriteObjects[0];
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public SpriteObject[] getSpriteObjects() {
        return spriteObjects;
    }

    public void setSpriteObjects(SpriteObject[] spriteObjects) {
        this.spriteObjects = spriteObjects;
    }

    public SpriteObject getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(SpriteObject currentSprite) {
        this.currentSprite = currentSprite;
    }

    @Override
    public String toString() {
        return name;
    }
}
