package net.aknyazev.game.editor.ui;

import com.badlogic.gdx.Gdx;
import net.aknyazev.game.editor.model.AbstractGameObject;
import net.aknyazev.game.editor.model.Atlas;
import net.aknyazev.game.editor.model.SpriteObject;
import net.aknyazev.game.editor.util.FileUtils;
import net.aknyazev.game.editor.world.RenderData;

/**
 * Created by MrKnyaz on 2016-04-22.
 */
public class UIController {

    protected RenderData renderData;
    protected UI ui;

    public UIController(RenderData renderData, UI ui) {
        this.renderData = renderData;
        this.ui = ui;
    }

    public void setDynamicItem(AbstractGameObject gameObject) {
        renderData.setDynamicItem(gameObject);
    }

    public void loadGraphics(String path) {
        ui.atlases = FileUtils.loadFromPack(path);
        //update atlases
        ui.atlasSelect.setItems(ui.atlases);
        ui.spriteSelect.setItems(ui.atlases[0].getSpriteObjects());
        renderData.setDynamicItem(null);
    }

}
