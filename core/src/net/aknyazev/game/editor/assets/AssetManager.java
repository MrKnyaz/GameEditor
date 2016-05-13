package net.aknyazev.game.editor.assets;

import net.aknyazev.game.editor.assets.shaders.AbstractShader;
import net.aknyazev.game.editor.assets.shaders.DefaultShader;
import net.aknyazev.game.editor.assets.shaders.GammaShader;
import net.aknyazev.game.editor.assets.shaders.LightShader;
import net.aknyazev.game.editor.model.LightObject;
import net.aknyazev.game.editor.util.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MrKnyaz on 2016-04-27.
 */
public class AssetManager {

    static AssetManager manager;

    Map<String, Atlas> atlases;

    DefaultShader defaultShader;
    List<AbstractShader> shaders = new ArrayList<AbstractShader>();
    List<LightObject> lights = new ArrayList<LightObject>();

    private  AssetManager(String path) {
        atlases = FileUtils.loadAtlas(path);
        defaultShader = new DefaultShader("default");
        shaders.add(defaultShader);
        shaders.add(new GammaShader("medium_dark", 30f));
        shaders.add(new GammaShader("very_dark", 5f));
        shaders.add(new LightShader("light_shader"));
        lights.add(new LightObject("Round Light"));
        lights.add(new LightObject("Round Light1"));
    }

    public static AssetManager getInstance() {
        return getInstance("packs", false);
    }

    public static AssetManager getInstance(String path, boolean reload) {
        if (manager == null || reload) {
            manager = new AssetManager(path);
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

    public DefaultShader getDefaultShader() {
        return defaultShader;
    }

    public List<AbstractShader> getShaders() {
        return shaders;
    }

    public LightObject[] getLightsArray() {
        LightObject[] result = new LightObject[lights.size()];
        int index = 0;
        for (LightObject light: lights) {
            result[index++] = light;
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
