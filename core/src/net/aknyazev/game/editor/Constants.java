package net.aknyazev.game.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by MrKnyaz on 24.03.2016.
 */
public class Constants {

    public static float VIEWPORT=40;
    public static float PIXPERUNIT=1980/VIEWPORT;

    public static OrthographicCamera cam;

    public static float getViewPortWidth() {
        return VIEWPORT;
    }
    public static float getViewPortHeight() {
        return VIEWPORT*Gdx.graphics.getHeight()/Gdx.graphics.getWidth();
    }

    public static float getPixelsPerUnit() {
        return Gdx.graphics.getWidth()/VIEWPORT;
    }
}
