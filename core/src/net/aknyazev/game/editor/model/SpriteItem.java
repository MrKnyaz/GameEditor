package net.aknyazev.game.editor.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.aknyazev.game.editor.Constants;

/**
 * Author: MrKnyaz
 * Date: 05.02.14
 */
public class SpriteItem extends AbstractItem {
    TextureRegion region;

    public SpriteItem(TextureRegion region) {
        this.region = region;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(region, posX, posY, region.getRegionWidth()/ Constants.PIXPERUNIT, region.getRegionHeight()/Constants.PIXPERUNIT);
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public SpriteItem copy() {
        SpriteItem result = new SpriteItem(region);
        result.setPosX(posX);
        result.setPosY(posY);
        result.setRotation(rotation);
        return result;
    }
}
