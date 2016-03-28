package net.aknyazev.game.editor.model;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
public class UIData {


    private Atlas[] atlases;
    private int state;
    private int currentAtlas;

    public UIData() {
        atlases = new Atlas[1];
    }

    public Atlas[] getAtlases() {
        return atlases;
    }

    public void setAtlases(Atlas[] atlases) {
        this.atlases = atlases;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCurrentAtlas() {
        return currentAtlas;
    }

    public void setCurrentAtlas(int currentAtlas) {
        this.currentAtlas = currentAtlas;
    }
}
