package net.aknyazev.game.editor.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.aknyazev.game.editor.util.FileUtils;

/**
 * Created by MrKnyaz on 2016-04-18.
 */
public class SaveLoadPanel extends Table {

    TextField pathToGraphics;
    TextButton loadGraphics;
    TextField pathToProject;
    TextButton loadProject;
    TextButton saveMap;

    public SaveLoadPanel(final UI ui, Skin skin) {
        pathToGraphics = new TextField("Path to images",skin);
        loadGraphics = new TextButton("Load Graphics", skin);
        pathToProject = new TextField("Path to project",skin);
        loadProject = new TextButton("Load Project", skin);
        saveMap = new TextButton("Save Map", skin);

        pad(5f);
        add(pathToGraphics);
        add(loadGraphics).right();
        row();
        add(pathToProject);
        add(loadProject).right();
        row();
        add(saveMap).colspan(2).left();

        //Load image data
        loadGraphics.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ui.atlases = FileUtils.loadFromPack("no path");
                //update atlases
                ui.atlasSelect.setItems(ui.atlases);
                ui.regionSelect.setItems(ui.atlases[0].getRegions());
                ui.renderData.setDynamicItem(null);
            }
        });

    }
}
