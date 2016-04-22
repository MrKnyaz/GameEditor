package net.aknyazev.game.editor.ui.input;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
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
    public void changeItem(int amount) {
        SelectBox<SpriteObject> spriteSelect = ui.getSpriteSelect();
        if (amount > 0) {
            int currentIndex = spriteSelect.getSelectedIndex();
            spriteSelect.setSelectedIndex(currentIndex < spriteSelect.getItems().size - 1 ? currentIndex + 1 : 0);
        } else {
            int currentIndex = spriteSelect.getSelectedIndex();
            spriteSelect.setSelectedIndex(currentIndex > 0 ? spriteSelect.getSelectedIndex() - 1 : spriteSelect.getItems().size - 1);
        }
    }

    @Override
    public void attachToMouse(int x, int y) {
        if (renderData.getDynamicItem() != null) {
            renderData.getDynamicItem().setPosX(renderData.getWorldX(x));
            renderData.getDynamicItem().setPosY(renderData.getWorldY(y));
        }
    }

    public Command submit(int screenX, int screenY) {
        return renderData.addItem(renderData.getDynamicItem().copy());
    }
}
