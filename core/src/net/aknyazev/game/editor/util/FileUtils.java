package net.aknyazev.game.editor.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import net.aknyazev.game.editor.model.Atlas;
import net.aknyazev.game.editor.model.UIData;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Author: aknyazev@kkb.kz
 * Date: 1/10/14
 */
public class FileUtils {
    /*public static void loadFromBraid(UIData data) {
        Texture texture = new Texture("data/pieces/c3_page_0.jpg");
        TextureRegion[] regions = new TextureRegion[texture.getHeight()/128*texture.getWidth()/128];
        for (int y = 0; y < texture.getHeight()/128; y++) {
            for (int x = 0; x < texture.getWidth()/128; x++) {
                regions[y*texture.getHeight()/128+x] = new TextureRegion(texture, x*128, y*128, 128, 128);
            }
        }
        Atlas atlas = new Atlas("Test", texture, regions);
        System.out.println(atlas);
        Atlas[] atlases = new Atlas[1];
        atlases[0] = atlas;
        data.setAtlases(atlases);
        data.setCurrentAtlas(0);
    }*/
    public static HashMap<String, Atlas> loadAtlas(String path) {
        HashMap<String, Atlas> result = new HashMap();
        File dir = new File(path);
        for (File file: dir.listFiles()) {
            String filename = file.getName().split("\\.")[0];
            String extension = file.getName().split("\\.")[1];
            if (extension.equalsIgnoreCase("pack")) {
                result.put(filename, new Atlas(filename, new TextureAtlas(Gdx.files.internal(path+"/"+file.getName()))));
            }
            //if (file.getName())
        }
        return result;
    }

    /*public static Atlas[] loadFromPack(String path) {
        TextureAtlas atlas;
        atlas = new TextureAtlas(Gdx.files.internal("packs/test1.pack"));
        Array<TextureAtlas.AtlasRegion> regions = atlas.getRegions();

        TextureRegion[] textureRegions = new TextureRegion[regions.size];
        int index = 0;
        for (TextureAtlas.AtlasRegion reg: regions) {
            textureRegions[index] = reg;
            index++;
        }
        Atlas inAtlas = new Atlas("2dforest", atlas.getTextures().iterator().next(), textureRegions);
        Atlas[] atlases = new Atlas[1];
        atlases[0] = inAtlas;
        return atlases;
        //Sprite sprite = atlas.createSprite("otherimagename");
        //NinePatch patch = atlas.createPatch("patchimagename");
    }*/
}
