package net.aknyazev.game.editor.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.aknyazev.game.editor.assets.AssetManager;
import net.aknyazev.game.editor.assets.Atlas;
import net.aknyazev.game.editor.model.SpriteObject;
import net.aknyazev.game.editor.model.UIData;
import net.aknyazev.game.editor.ui.input.MapInputProcessor;
import net.aknyazev.game.editor.ui.input.PhysicsRectangleTool;
import net.aknyazev.game.editor.ui.input.SelectionTool;
import net.aknyazev.game.editor.ui.input.SpriteTool;
import net.aknyazev.game.editor.world.RenderData;

/**
 * Author: MrKnyaz
 * Date: 21.12.13
 */
public class UI {

    Stage stage;
    MapInputProcessor mapInputProcessor;
    Skin skin;

    //data
    UIData uiData;
    RenderData renderData;


    //ui
    TextButton selectionToolButton;
    TextButton spriteToolButton;
    SelectBox<Atlas> atlasSelect;
    SelectBox<SpriteObject> spriteSelect;

    TextButton physicsToolButton;
    SelectBox physicsSelect;

    SaveLoadPanel saveLoadPanel;
    LayerPanel layerPanel;


    //controllers
    SelectionTool selectionTool;
    SpriteTool spriteTool;
    PhysicsRectangleTool objectDrawingState;
    UIController mainController;

    public UI(RenderData renderData) {
        this.renderData = renderData;
        this.uiData = new UIData();
        mainController = new UIController(renderData, uiData, this);
        selectionTool = new SelectionTool(renderData, this);
        spriteTool = new SpriteTool(renderData, this);
        objectDrawingState = new PhysicsRectangleTool(renderData, this);

        stage = new Stage();
        //unfocus textfields
        stage.getRoot().addCaptureListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!(event.getTarget() instanceof TextField)) stage.setKeyboardFocus(null);
                return false;
            }
        });
        mapInputProcessor = new MapInputProcessor(spriteTool);
        InputMultiplexer inputs = new InputMultiplexer();
        inputs.addProcessor(stage);
        inputs.addProcessor(mapInputProcessor);
        Gdx.input.setInputProcessor(inputs);

        skin = new Skin(Gdx.files.internal("skins/Holo-dark-ldpi.json"));
        skin.getAtlas().getTextures().iterator().next().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        initUIElements();
        addControl();
        updateUI();
        updateAtlasList();
    }

    private void initUIElements() {
        Table table = new Table();
        stage.addActor(table);
        table.setSize(Gdx.graphics.getWidth(), 100);
        table.setPosition(0, 60);
        //table.debug();

        //Add ui
        atlasSelect = new SelectBox(skin);
        atlasSelect.setSize(1000f, 10f);
        spriteSelect = new SelectBox(skin);

        selectionToolButton = new TextButton("Selection", skin);
        spriteToolButton = new TextButton("Sprite Tool", skin);
        physicsToolButton = new TextButton("Physics Tool", skin);

        table.add(saveLoadPanel = new SaveLoadPanel(this, skin)).top();
        //sprite tool layout
        Table spriteTable = new Table().pad(5f);
        spriteTable.add(spriteToolButton).colspan(2).left();
        spriteTable.row();
        spriteTable.add(new Label("Atlas:", skin)).right();
        spriteTable.add(atlasSelect).left();
        spriteTable.add(new Label("Sprite:", skin)).right();
        spriteTable.add(spriteSelect).left();
        table.add(spriteTable).top();

        table.add(selectionToolButton).top().pad(5f);
        table.add(physicsToolButton).top().pad(5f);

        table.add(layerPanel = new LayerPanel(this, renderData, skin)).top();

    }

    private void addControl() {
        //Adding state control, i.e. Selection, Drawing or ObjectDrawing
        selectionToolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(selectionTool);
                updateButtonsColors();
            }
        });
        spriteToolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(spriteTool);
                updateButtonsColors();
            }
        });
        physicsToolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(objectDrawingState);
                updateButtonsColors();
            }
        });
        //atlas selected event
        atlasSelect.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                updateSpritesList();
            }
        });
        //Region selected event
        spriteSelect.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                mainController.setDynamicItem(new SpriteObject(spriteSelect.getSelected()));
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

    public void updateAtlasList() {
        atlasSelect.setItems(AssetManager.getInstance().getAtlasArray());
        updateSpritesList();
    }
    public void updateSpritesList() {
        Atlas atlas = atlasSelect.getSelected();
        spriteSelect.setItems(uiData.getSpriteObjects(atlas));
    }

    public void updateLayerInfo() {
        layerPanel.updateFields();
    }

    public void updateLayersList() {
        layerPanel.updateLayersList();
    }
    /*public void updateFromModels() {
        Atlas[] atlases = uiData.getAtlases();
        atlasSelect.setItems(atlases);
        int currentAtlas = uiData.getCurrentAtlas();
        atlasSelect.setSelection(currentAtlas);
        TextureRegion[] regions = atlases[currentAtlas].getRegions();
        int currentRegion = atlases[currentAtlas].getCurrentSprite();
        spriteSelect.setItems(regions);
        spriteSelect.setSelection(currentRegion);
    }*/

    public void nextSprite() {
        int currentIndex = spriteSelect.getSelectedIndex();
        spriteSelect.setSelectedIndex(currentIndex < spriteSelect.getItems().size - 1 ? currentIndex + 1 : 0);
    }
    public void previousSprite() {
        int currentIndex = spriteSelect.getSelectedIndex();
        spriteSelect.setSelectedIndex(currentIndex > 0 ? spriteSelect.getSelectedIndex() - 1 : spriteSelect.getItems().size - 1);
    }
    public void dispose() {
        stage.dispose();
    }

    public UIController getMainController() {
        return mainController;
    }

}
