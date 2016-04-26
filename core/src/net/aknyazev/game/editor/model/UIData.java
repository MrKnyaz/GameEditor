package net.aknyazev.game.editor.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
public class UIData {

    TextureAtlas textureAtlas;

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    public SpriteObject[] getSpriteObjects() {
        Array<TextureAtlas.AtlasRegion> regionArray = textureAtlas.getRegions();
        SpriteObject[] result = new SpriteObject[regionArray.size];
        int i = 0;
        for (TextureAtlas.AtlasRegion region: regionArray) {
            result[i++] = new SpriteObject(region, region.name);
        }
        return result;
    }
}
