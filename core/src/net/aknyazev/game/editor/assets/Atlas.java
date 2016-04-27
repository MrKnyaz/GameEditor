package net.aknyazev.game.editor.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Author: MrKnyaz
 * Date: 22.12.13
 */
public class Atlas {

    private String name;
    TextureAtlas textureAtlas;

    public Atlas(String name, TextureAtlas textureAtlas) {
        this.name = name;
        this.textureAtlas = textureAtlas;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public void dispose() {
        textureAtlas.dispose();
    }
    @Override
    public String toString() {
        return name;
    }
}
