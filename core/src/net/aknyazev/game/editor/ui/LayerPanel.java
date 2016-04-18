package net.aknyazev.game.editor.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by MrKnyaz on 2016-04-18.
 */
public class LayerPanel extends Table {

    SelectBox layerSelect, shadersSelect;
    CheckBox allLayersCheck;
    TextField speedTF;
    TextButton backButton, frontButton, saveButton, addNewButton;


    public LayerPanel(final UI ui, Skin skin) {
        layerSelect = new SelectBox(skin);
        layerSelect.setItems(ui.renderData.getLayers());
        shadersSelect = new SelectBox(skin);
        allLayersCheck = new CheckBox("All layers", skin);
        allLayersCheck.setChecked(ui.renderData.isAllLayers());
        speedTF = new TextField("", skin);
        speedTF.setSize(1f, 1f);
        backButton = new TextButton("Back", skin);
        frontButton = new TextButton("Front", skin);
        saveButton = new TextButton("Save", skin);
        addNewButton = new TextButton("Add New", skin);

        pad(5f);
        add(new Label("Layer:", skin)).right();
        add(layerSelect).left();
        add(allLayersCheck).colspan(2);
        row();
        add(new Label("Speed:", skin)).right();
        add(speedTF).left();
        add(new Label("Shaders:", skin)).right();
        add(shadersSelect).left();
        row();
        add(new Label("Order:", skin)).right();
        add(backButton).left();
        add(new Label("Order:", skin)).right();
        add(frontButton).left();
        row();
        add(saveButton).left();
        add(addNewButton).left().colspan(3);


        //actions
        layerSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ui.renderData.setCurrentLayer(layerSelect.getSelectedIndex());
            }
        });

    }
}
