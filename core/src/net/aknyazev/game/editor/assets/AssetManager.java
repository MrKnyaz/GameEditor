package net.aknyazev.game.editor.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import net.aknyazev.game.editor.model.Atlas;
import net.aknyazev.game.editor.util.FileUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by MrKnyaz on 2016-04-27.
 */
public class AssetManager {

    static AssetManager manager;

    Map<String, Atlas> atlases;

    private  AssetManager(String path) {
        atlases = FileUtils.loadAtlas(path);
    }

    public static AssetManager getInstance() {
        if (manager == null) {
            manager = new AssetManager("packs");
        }
        return manager;
    }

    public Atlas[] getAtlasArray() {
        Atlas[] result = new Atlas[atlases.size()];
        int index = 0;
        for (Atlas atlas: atlases.values()) {
            result[index++] = atlas;
        }
        return result;
    }
    public void dispose() {
        manager = null;
        if (atlases != null) {
            for (Atlas atlas: atlases.values()) {
                atlas.dispose();
            }
        }
    }
}
