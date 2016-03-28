package net.aknyazev.game.editor.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.aknyazev.game.editor.model.Atlas;
import net.aknyazev.game.editor.ui.control.DrawingState;
import net.aknyazev.game.editor.ui.control.MapInputProcessor;
import net.aknyazev.game.editor.model.RenderData;
import net.aknyazev.game.editor.model.UIData;
import net.aknyazev.game.editor.ui.control.ObjectDrawingState;
import net.aknyazev.game.editor.ui.control.SelectionState;
import net.aknyazev.game.editor.util.FileUtils;

/**
 * Author: MrKnyaz
 * Date: 21.12.13
 */
public class UI {

    Stage stage;
    MapInputProcessor mapInputProcessor;
    Skin skin;

    //data
    private SelectionState selectionState;
    private DrawingState drawingState;
    private ObjectDrawingState objectDrawingState;
    private Atlas[] atlases;
    private Atlas currentAtlas;
    UIData uiData;
    RenderData renderData;


    //ui
    TextField pathToGraphics;
    TextButton submitPath;
    SelectBox<Atlas> atlasSelect;
    SelectBox regionSelect;
    SelectBox layerSelect;
    CheckBox allLayersCheck;
    TextButton selection;
    TextButton drawing;
    TextButton objectDrawing;
    TextButton save;

    public UI(RenderData renderData) {
        this.renderData = renderData;
        this.uiData = new UIData();
        selectionState = new SelectionState(renderData, this);
        drawingState = new DrawingState(renderData, this);
        objectDrawingState = new ObjectDrawingState(renderData, this);

        stage = new Stage();
        mapInputProcessor = new MapInputProcessor(drawingState);
        InputMultiplexer inputs = new InputMultiplexer();
        inputs.addProcessor(stage);
        inputs.addProcessor(mapInputProcessor);
        Gdx.input.setInputProcessor(inputs);

        skin = new Skin(Gdx.files.internal("skins/Holo-dark-ldpi.json"));
        skin.getAtlas().getTextures().iterator().next().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        initUIElements();
        addControl();
        updateUI();
    }

    private void initUIElements() {
        Table table = new Table();
        stage.addActor(table);
        table.setSize(Gdx.graphics.getWidth(), 100);
        table.setPosition(0, 10);
        //table.debug();

        //Add ui
        pathToGraphics = new TextField("Path to images",skin);
        atlasSelect = new SelectBox<Atlas>(skin);
        regionSelect = new SelectBox<TextureRegion>(skin);
        layerSelect = new SelectBox(skin);
        allLayersCheck = new CheckBox("All layers", skin);

        submitPath = new TextButton("Ok", skin);
        selection = new TextButton("Selection", skin);
        drawing = new TextButton("Drawing", skin);
        objectDrawing = new TextButton("ObjectDrawing", skin);
        save = new TextButton("Save map", skin);

        table.add(pathToGraphics);
        table.add(submitPath).align(Align.left);
        table.add(selection).right();
        table.add(drawing).right();
        table.add(objectDrawing).right();
        table.add(save).right().colspan(2);
        table.row();
        table.add(new Label("Atlas:", skin)).right();
        table.add(atlasSelect).left();
        table.add(new Label("Region:", skin)).right();
        table.add(regionSelect).left();
        table.add(new Label("Layer:", skin)).right();
        table.add(layerSelect).left();
        table.add(allLayersCheck);

    }

    private void addControl() {
        //Adding state control, i.e. Selection, Drawing or ObjectDrawing
        selection.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(selectionState);
                renderData.setDynamicItem(null);
                updateButtonsColors();
            }
        });
        drawing.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(drawingState);
                renderData.setDynamicItem(null);
                updateButtonsColors();
            }
        });
        objectDrawing.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(objectDrawingState);
                renderData.setDynamicItem(null);
                updateButtonsColors();
            }
        });
        //Load image data
        submitPath.addListener((new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                atlases = FileUtils.loadFromPack("no path");
                //update atlases
                atlasSelect.setItems(atlases);
                regionSelect.setItems(atlases[0].getRegions());
                renderData.setDynamicItem(null);
            }
        }));
        //Atlas selected event
        atlasSelect.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                currentAtlas = atlasSelect.getSelected();
            }
        });
        //Region selected event
        regionSelect.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //System.out.println("region "+regionSelect.getSelectionIndex());
                currentAtlas.setCurrentRegion(regionSelect.getSelectedIndex());
            }
        });
    }
    public void resize(int width, int height) {

        stage.getViewport().update(width, height);
    }

    public void render(float delta) {
        //updateUI();
        stage.act(delta);
        stage.draw();
        //Table.drawDebug(stage);
    }

    public Stage getStage() {
        return stage;
    }

    public void updateUI() {
        updateButtonsColors();
        //updateFromModels();
    }
    public void updateButtonsColors() {
        //first state buttons and InputProcessor state
        if (mapInputProcessor.getState() instanceof SelectionState) {
            selection.setColor(0, 1, 1, 1);
        } else {
            selection.setColor(1,1,1,1);
        }
        if (mapInputProcessor.getState() instanceof DrawingState) {
            drawing.setColor(0, 1, 1, 1);
        } else {
            drawing.setColor(1,1,1,1);
        }
        if (mapInputProcessor.getState() instanceof ObjectDrawingState) {
            objectDrawing.setColor(0,1,1,1);
        } else {
            objectDrawing.setColor(1,1,1,1);
        }
    }
    /*public void updateFromModels() {
        Atlas[] atlases = uiData.getAtlases();
        atlasSelect.setItems(atlases);
        int currentAtlas = uiData.getCurrentAtlas();
        atlasSelect.setSelection(currentAtlas);
        TextureRegion[] regions = atlases[currentAtlas].getRegions();
        int currentRegion = atlases[currentAtlas].getCurrentRegion();
        regionSelect.setItems(regions);
        regionSelect.setSelection(currentRegion);
    }*/

    public void dispose() {
        stage.dispose();
    }

    Atlas[] getAtlases() {
        return atlases;
    }

    public Atlas getCurrentAtlas() {
        return currentAtlas;
    }

    public void setCurrentAtlas(Atlas currentAtlas) {
        this.currentAtlas = currentAtlas;
    }

    public void nextRegion() {
        int currentIndex = regionSelect.getSelectedIndex();
        regionSelect.setSelectedIndex(currentIndex<regionSelect.getItems().size-1?currentIndex+1:0);
    }
    public void previousRegion() {
        int currentIndex = regionSelect.getSelectedIndex();
        regionSelect.setSelectedIndex(currentIndex>0?regionSelect.getSelectedIndex()-1:regionSelect.getItems().size-1);
    }
}
