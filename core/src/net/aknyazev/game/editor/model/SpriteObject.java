package net.aknyazev.game.editor.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.aknyazev.game.editor.Constants;

/**
 * Author: MrKnyaz
 * Date: 05.02.14
 */
public class SpriteObject extends AbstractGameObject {
    TextureRegion region;

    public SpriteObject(TextureRegion region) {
        this.region = region;
    }

    public void draw(SpriteBatch batch) {
        float spriteWidth = region.getRegionWidth()/ Constants.PIXPERUNIT * scaleX;
        float spriteHeight = region.getRegionHeight()/Constants.PIXPERUNIT * scaleY;
        batch.draw(region, posX-spriteWidth/2, posY-spriteHeight/2, spriteWidth, spriteHeight);
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public SpriteObject copy() {
        SpriteObject result = new SpriteObject(region);
        result.setPosX(posX);
        result.setPosY(posY);
        result.setRotation(rotation);
        result.setScaleX(scaleX);
        result.setScaleY(scaleY);
        return result;
    }

    public String toString() {
        return region.toString();
    }
}
