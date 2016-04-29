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
    String atlasName;
    String regionName;

    public SpriteObject(String atlasName, String regionName, TextureRegion region) {
        this.region = region;
        this.atlasName = atlasName;
        this.regionName = regionName;
    }

    public SpriteObject(SpriteObject gameObject) {
        super(gameObject);
        this.region = gameObject.region;
        this.atlasName = gameObject.atlasName;
        this.regionName = gameObject.regionName;
    }

    public void draw(SpriteBatch batch) {
        batch.setColor(1, 1, 1, opacity);
        int flipByX = flipX ? -1 : 1;
        int flipByY = flipY ? -1 : 1;
        float spriteWidth = region.getRegionWidth()/ Constants.PIXPERUNIT * scaleX*flipByX;
        float spriteHeight = region.getRegionHeight()/Constants.PIXPERUNIT * scaleY*flipByY;
        batch.draw(region, posX-spriteWidth/2, posY-spriteHeight/2, spriteWidth, spriteHeight);
        batch.setColor(1, 1, 1, 1);
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public String toString() {
        return region.toString();
    }
}
