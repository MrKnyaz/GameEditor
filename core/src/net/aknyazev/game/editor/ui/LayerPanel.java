package net.aknyazev.game.editor.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.aknyazev.game.editor.model.Layer;

import java.util.ArrayList;

/**
 * Created by MrKnyaz on 2016-04-18.
 */
public class LayerPanel extends Table {

    SelectBox layerSelect, shadersSelect;
    CheckBox allLayersCheck, animatedCheck;
    TextField speedTF, nameTF;
    TextButton backButton, frontButton, saveButton, addNewButton;


    public LayerPanel(final UI ui, Skin skin) {
        layerSelect = new SelectBox(skin);
        layerSelect.setItems(ui.renderData.getLayers().toArray());
        shadersSelect = new SelectBox(skin);
        allLayersCheck = new CheckBox("All layers", skin);
        allLayersCheck.setChecked(ui.renderData.isAllLayers());
        animatedCheck = new CheckBox("Animated", skin);
        speedTF = new TextField("", skin);
        nameTF = new TextField("", skin);
        backButton = new TextButton("Back", skin);
        frontButton = new TextButton("Front", skin);
        saveButton = new TextButton("Save", skin);
        addNewButton = new TextButton("Add New", skin);
        Layer layer = ui.renderData.getLayers().get(ui.renderData.getCurrentLayer());
        updateFields(layer);

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
        add(shadersSelect).left();
        row();
        add(new Label("Order:", skin)).right().colspan(2);
        add(backButton).left();
        add(frontButton).left();
        row();
        add(saveButton).left();
        add(addNewButton).left().colspan(3);


        //actions
        layerSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("CHANGED");
                ui.renderData.setCurrentLayer(layerSelect.getSelectedIndex());
                updateFields(ui.renderData.getLayers().get(layerSelect.getSelectedIndex()));
            }
        });
        addNewButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                try {
                    Layer newLayer = new Layer(nameTF.getText());
                    newLayer.setSpeed(Float.parseFloat(speedTF.getText()));
                    ArrayList<Layer> layers = ui.renderData.getLayers();
                    layers.add(newLayer);
                    //ui.renderData.setCurrentLayer(layers.size()-1);
                    layerSelect.setItems(layers.toArray());
                    layerSelect.setSelectedIndex(layers.size()-1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ArrayList<Layer> layers = ui.renderData.getLayers();
                int currentLayer = ui.renderData.getCurrentLayer();
                if (currentLayer < layers.size() - 1) {
                    Layer tmpLayer = layers.get(currentLayer);
                    layers.set(currentLayer, layers.get(currentLayer+1));
                    layers.set(currentLayer+1, tmpLayer);
                    ui.renderData.setCurrentLayer(++currentLayer);
                    layerSelect.setItems(layers.toArray());
                }
            }
        });
        frontButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ArrayList<Layer> layers = ui.renderData.getLayers();
                int currentLayer = ui.renderData.getCurrentLayer();
                if (currentLayer > 0) {
                    Layer tmpLayer = layers.get(currentLayer);
                    layers.set(currentLayer, layers.get(currentLayer-1));
                    layers.set(currentLayer-1, tmpLayer);
                    ui.renderData.setCurrentLayer(--currentLayer);
                    layerSelect.setItems(layers.toArray());
                }
            }
        });

    }

    private void updateFields(Layer layer) {
        nameTF.setText(layer.toString());
        speedTF.setText(Float.toString(layer.getSpeed()));
    }
}
