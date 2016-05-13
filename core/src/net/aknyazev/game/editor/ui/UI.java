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
import net.aknyazev.game.editor.model.LightObject;
import net.aknyazev.game.editor.model.SpriteObject;
import net.aknyazev.game.editor.model.UIData;
import net.aknyazev.game.editor.ui.input.*;
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

    TextButton lightToolButton;
    SelectBox<LightObject> lightSelect;
    TextButton physicsToolButton;
    SelectBox physicsSelect;

    SaveLoadPanel saveLoadPanel;
    LayerPanel layerPanel;


    //controllers
    SelectionTool selectionTool;
    SpriteTool spriteTool;
    LightTool lightTool;
    PhysicsRectangleTool objectDrawingState;
    UIController mainController;

    public UI(RenderData renderData) {
        this.renderData = renderData;
        this.uiData = new UIData();
        mainController = new UIController(renderData, uiData, this);
        selectionTool = new SelectionTool(renderData, this);
        spriteTool = new SpriteTool(renderData, this);
        lightTool = new LightTool(renderData, this);
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
        spriteSelect = new SelectBox(skin);
        lightSelect = new SelectBox(skin);

        selectionToolButton = new TextButton("Selection", skin);
        spriteToolButton = new TextButton("Sprite Tool", skin);
        lightToolButton = new TextButton("Light Tool", skin);
        physicsToolButton = new TextButton("Physics Tool", skin);

        table.add(saveLoadPanel = new SaveLoadPanel(this, skin)).top();
        //sprite tool layout
        Table column2 = new Table().pad(5f);
        column2.add(spriteToolButton).colspan(4).left();
        column2.row();
        column2.add(new Label("Atlas:", skin)).right();
        column2.add(atlasSelect).left();
        column2.add(new Label("Sprite:", skin)).right();
        column2.add(spriteSelect).left();
        //LightTool layout
        column2.row();
        column2.add(lightToolButton).colspan(4).left();
        column2.row();
        column2.add(new Label("Light:", skin)).right();
        column2.add(lightSelect).colspan(3).left();
        table.add(column2).top();

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
        lightToolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                mapInputProcessor.setState(lightTool);
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
        //Light selected
        lightSelect.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                mainController.setDynamicItem(new LightObject(lightSelect.getSelected()));
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
        lightSelect.setItems(AssetManager.getInstance().getLightsArray());
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
        if (mapInputProcessor.getState() instanceof LightTool) {
            lightToolButton.setColor(0, 1, 1, 1);
        } else {
            lightToolButton.setColor(1, 1, 1, 1);
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
        spriteSelect.setItems(atlas.getSpriteObjects());
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

    public void nextLight() {
        int currentIndex = lightSelect.getSelectedIndex();
        lightSelect.setSelectedIndex(currentIndex < lightSelect.getItems().size - 1 ? currentIndex + 1 : 0);
    }

    public void previousLight() {
        int currentIndex = lightSelect.getSelectedIndex();
        lightSelect.setSelectedIndex(currentIndex > 0 ? lightSelect.getSelectedIndex() - 1 : lightSelect.getItems().size - 1);
    }

    public void dispose() {
        stage.dispose();
    }

    public UIController getMainController() {
        return mainController;
    }

}
