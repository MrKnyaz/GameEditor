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

    TextField pathToProject;
    TextButton loadProject;
    TextButton saveMap;

    public SaveLoadPanel(final UI ui, Skin skin) {
        pathToProject = new TextField("Path to project",skin);
        loadProject = new TextButton("Load Project", skin);
        saveMap = new TextButton("Save Map", skin);

        pad(5f);
        add(pathToProject);
        add(loadProject).right();
        row();
        add(saveMap).colspan(2).left();


    }

}
