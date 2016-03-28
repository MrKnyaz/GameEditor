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
    private TextureRegion[] regions;
    private int currentRegion;

    public Atlas(String name, Texture texture, TextureRegion[] regions) {
        this.name = name;
        this.texture = texture;
        this.regions = regions;
    }

    public void nextRegion() {
        if (currentRegion < regions.length -1) {
            currentRegion++;
        } else {
            currentRegion = 0;
        }
    }

    public void previousRegion() {
        if (currentRegion > 0) {
            currentRegion--;
        } else {
            currentRegion = regions.length-1;
        }
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

    public TextureRegion[] getRegions() {
        return regions;
    }

    public void setRegions(TextureRegion[] regions) {
        this.regions = regions;
    }

    public int getCurrentRegion() {
        return currentRegion;
    }

    public void setCurrentRegion(int currentRegion) {
        this.currentRegion = currentRegion;
    }

    @Override
    public String toString() {
        return name;
    }
}
