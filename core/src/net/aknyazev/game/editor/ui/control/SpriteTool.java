package net.aknyazev.game.editor.ui.control;


import com.badlogic.gdx.Gdx;
import net.aknyazev.game.editor.Constants;
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
        Atlas currentAtlas = ui.getCurrentAtlas();
        if (amount > 0) {
            ui.nextRegion();
        } else {
            ui.previousRegion();
        }
        renderData.setDynamicItem(new SpriteObject(currentAtlas.getRegions()[currentAtlas.getCurrentRegion()]));
        renderData.getDynamicItem().setPosX(Gdx.input.getX()/Constants.getPixelsPerUnit());
        renderData.getDynamicItem().setPosY(Constants.getViewPortHeight() - Gdx.input.getY() / Constants.getPixelsPerUnit());

    }

    @Override
    public void attachToMouse(int x, int y) {

        System.out.println(x + ", " + y);
        //System.out.println("MOUSE:"+Mouse.getX() + ", " + y);
        if (renderData.getDynamicItem() != null) {
            //System.out.println(x/Constants.getViewPortWidth()+", "+y/Constants.getViewPortHeight());
            renderData.getDynamicItem().setPosX(x/Constants.getPixelsPerUnit());
            System.out.println(Constants.getViewPortHeight()+"      "+y/Constants.getViewPortHeight()+"             "+y);
            renderData.getDynamicItem().setPosY(Constants.getViewPortHeight()-y/Constants.getPixelsPerUnit());
        }
    }

    public Command submit(int screenX, int screenY) {
        System.out.println(screenX+" "+screenY);
        //renderData.getLayers()[0].getItems().add(renderData.getDynamicItem().copy());
        return renderData.addItem(renderData.getDynamicItem().copy());
    }
}
