package net.aknyazev.game.editor.ui.input;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import net.aknyazev.game.editor.assets.AssetManager;
import net.aknyazev.game.editor.model.*;
import net.aknyazev.game.editor.ui.UI;
import net.aknyazev.game.editor.ui.command.Command;
import net.aknyazev.game.editor.world.RenderData;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
public class SpriteTool extends AbstractTool {

    public SpriteTool(RenderData renderData, UI ui) {
        super(renderData, ui);
    }

    @Override
    public void init() {
        ui.nextSprite();
    }

    @Override
    public void changeItem(int amount) {
        if (amount > 0) {
            ui.nextSprite();
        } else {
            ui.previousSprite();
        }
        attachToMouse(Gdx.input.getX(), Gdx.input.getY());
    }

    public Command submit(int screenX, int screenY) {
        return renderData.addItem(new SpriteObject((SpriteObject)renderData.getDynamicItem()));
    }
}
