package net.aknyazev.game.editor.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.aknyazev.game.editor.assets.AssetManager;
import net.aknyazev.game.editor.assets.shaders.AbstractShader;
import net.aknyazev.game.editor.model.Layer;
import net.aknyazev.game.editor.world.RenderData;

/**
 * Created by MrKnyaz on 2016-04-18.
 */
public class LayerPanel extends Table {

    RenderData renderData;

    UIController mainController;

    SelectBox layerSelect;
    SelectBox shaderSelect;
    CheckBox allLayersCheck, animatedCheck;
    TextField speedTF, nameTF;
    TextButton backButton, frontButton, updateButton, addNewButton;



    public LayerPanel(final UI ui, final RenderData renderData, Skin skin) {
        this.renderData = renderData;
        this.mainController = ui.getMainController();

        layerSelect = new SelectBox(skin);
        shaderSelect = new SelectBox(skin);
        allLayersCheck = new CheckBox("All layers", skin);
        allLayersCheck.setChecked(renderData.isAllLayers());
        animatedCheck = new CheckBox("Animated", skin);
        speedTF = new TextField("", skin);
        nameTF = new TextField("", skin);
        backButton = new TextButton("Back", skin);
        frontButton = new TextButton("Front", skin);
        updateButton = new TextButton("Update", skin);
        addNewButton = new TextButton("Add New", skin);
        updateShadersList();
        updateLayersList();
        updateFields();

        pad(5f);
        add(new Label("Layer:", skin)).right();
        add(layerSelect).left();
        add(allLayersCheck).colspan(2);
        row();
        add(new Label("Name:", skin)).right();
        add(nameTF).left();
        add(animatedCheck).colspan(2);
        row();
        add(new Label("Speed:", skin)).right();
        add(speedTF).left();
        add(new Label("Shader:", skin)).right();
        add(shaderSelect).left();
        row();
        add(new Label("Order:", skin)).right().colspan(2);
        add(backButton).left();
        add(frontButton).left();
        row();
        add(updateButton).left();
        add(addNewButton).left().colspan(3);

        //actions
        layerSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainController.setCurrentLayer(layerSelect.getSelectedIndex());
            }
        });
        addNewButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mainController.saveOrUpdateLayer(nameTF.getText(), speedTF.getText(), (AbstractShader) shaderSelect.getSelected(), false, null);
                layerSelect.setSelectedIndex(renderData.getLayers().size() - 1);
            }
        });
        updateButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mainController.saveOrUpdateLayer(nameTF.getText(), speedTF.getText(), (AbstractShader)shaderSelect.getSelected(), false, (Layer)layerSelect.getSelected());
            }
        });
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainController.rearrangeLayer(+1);
            }
        });
        frontButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainController.rearrangeLayer(-1);
            }
        });

    }

    public void updateShadersList() {
        AssetManager assetManager = AssetManager.getInstance();
        shaderSelect.setItems(assetManager.getShaders().toArray());
    }
    public void updateFields() {
        Layer layer = renderData.getLayers().get(renderData.getCurrentLayerIndex());
        nameTF.setText(layer.toString());
        speedTF.setText(Float.toString(layer.getSpeed()));
        shaderSelect.setSelected(layer.getShader());
    }

    public void updateLayersList() {
        layerSelect.setItems(renderData.getLayers().toArray());
    }
}
