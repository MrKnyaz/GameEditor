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
import net.aknyazev.game.editor.model.Atlas;
import net.aknyazev.game.editor.ui.control.SpriteTool;
import net.aknyazev.game.editor.ui.control.MapInputProcessor;
import net.aknyazev.game.editor.world.RenderData;
import net.aknyazev.game.editor.model.UIData;
import net.aknyazev.game.editor.ui.control.PhysicsRectangleTool;
import net.aknyazev.game.editor.ui.control.SelectionTool;

/**
 * Author: MrKnyaz
 * Date: 21.12.13
 */
public class UI {

    Stage stage;
    MapInputProcessor mapInputProcessor;
    Skin skin;

    //data
    SelectionTool selectionState;
    SpriteTool drawingState;
    PhysicsRectangleTool objectDrawingState;
    Atlas[] atlases;
    Atlas currentAtlas;
    UIData uiData;
    RenderData renderData;


    //ui
    TextButton selectionToolButton;
    TextButton spriteToolButton;
    SelectBox<Atlas> atlasSelect;
    SelectBox regionSelect;

    TextButton physicsToolButton;
    SelectBox physicsSelect;


    public UI(RenderData renderData) {
        this.renderData = renderData;
        this.uiData = new UIData();
        selectionState = new SelectionTool(renderData, this);
        drawingState = new SpriteTool(renderData, this);
        objectDrawingState = new PhysicsRectangleTool(renderData, this);

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
        table.setPosition(0, 60);
        //table.debug();

        //Add ui
        atlasSelect = new SelectBox<Atlas>(skin);
        regionSelect = new SelectBox<TextureRegion>(skin);

        selectionToolButton = new TextButton("Selection", skin);
        spriteToolButton = new TextButton("Sprite Tool", skin);
        physicsToolButton = new TextButton("Physics Tool", skin);

        table.add(new SaveLoadPanel(this, skin)).top();
        //sprite tool layout
        Table spriteToolTable = new Table().pad(5f);
        spriteToolTable.add(spriteToolButton).colspan(2).left();
        spriteToolTable.row();
        spriteToolTable.add(new Label("Atlas:", skin)).right();
        spriteToolTable.add(atlasSelect).left();
        spriteToolTable.add(new Label("Region:", skin)).right();
        spriteToolTable.add(regionSelect).left();
        table.add(spriteToolTable).top();

        table.add(selectionToolButton).top().pad(5f);
        table.add(physicsToolButton).top().pad(5f);

        table.add(new LayerPanel(this, skin)).top();

    }

    private void addControl() {
        //Adding state control, i.e. Selection, Drawing or ObjectDrawing
        selectionToolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(selectionState);
                renderData.setDynamicItem(null);
                updateButtonsColors();
            }
        });
        spriteToolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(drawingState);
                renderData.setDynamicItem(null);
                updateButtonsColors();
            }
        });
        physicsToolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(objectDrawingState);
                renderData.setDynamicItem(null);
                updateButtonsColors();
            }
        });
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
        if (mapInputProcessor.getState() instanceof SelectionTool) {
            selectionToolButton.setColor(0, 1, 1, 1);
        } else {
            selectionToolButton.setColor(1, 1, 1, 1);
        }
        if (mapInputProcessor.getState() instanceof SpriteTool) {
            spriteToolButton.setColor(0, 1, 1, 1);
        } else {
            spriteToolButton.setColor(1, 1, 1, 1);
        }
        if (mapInputProcessor.getState() instanceof PhysicsRectangleTool) {
            physicsToolButton.setColor(0, 1, 1, 1);
        } else {
            physicsToolButton.setColor(1, 1, 1, 1);
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
