package net.aknyazev.game.editor.model;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import net.aknyazev.game.editor.assets.Atlas;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
public class UIData {

    public SpriteObject[] getSpriteObjects(Atlas currentAtlas) {
        Array<TextureAtlas.AtlasRegion> regionArray = currentAtlas.getTextureAtlas().getRegions();
        SpriteObject[] result = new SpriteObject[regionArray.size];
        int i = 0;
        for (TextureAtlas.AtlasRegion region: regionArray) {
            result[i++] = new SpriteObject(currentAtlas.getName(), region.name, region);
        }
        return result;
    }
}
