package net.aknyazev.game.editor.ui.input;


import com.badlogic.gdx.Gdx;
import net.aknyazev.game.editor.model.LightObject;
import net.aknyazev.game.editor.model.SpriteObject;
import net.aknyazev.game.editor.ui.UI;
import net.aknyazev.game.editor.ui.command.Command;
import net.aknyazev.game.editor.world.RenderData;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
public class LightTool extends AbstractTool {

    public LightTool(RenderData renderData, UI ui) {
        super(renderData, ui);
    }

    @Override
    public void init() {
        ui.nextLight();
    }

    @Override
    public void changeItem(int amount) {
        if (amount > 0) {
            ui.nextLight();
        } else {
            ui.previousLight();
        }
        attachToMouse(Gdx.input.getX(), Gdx.input.getY());
    }

    public Command submit(int screenX, int screenY) {
        return renderData.addItem(new LightObject((LightObject)renderData.getDynamicItem()));
    }
}
