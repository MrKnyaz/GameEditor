package net.aknyazev.game.editor.ui.control;


import com.badlogic.gdx.Gdx;
import net.aknyazev.game.editor.Constants;
import net.aknyazev.game.editor.model.*;
import net.aknyazev.game.editor.ui.UI;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
public class DrawingState extends AbstractControlState {

    public DrawingState(RenderData renderData, UI ui) {
        super(renderData, ui);
    }

    @Override
    public void scrolled(int amount) {
        Atlas currentAtlas = ui.getCurrentAtlas();
        if (amount > 0) {
            ui.nextRegion();
        } else {
            ui.previousRegion();
        }
        renderData.setDynamicItem(new SpriteItem(currentAtlas.getRegions()[currentAtlas.getCurrentRegion()]));
        renderData.getDynamicItem().setPosX(Gdx.input.getX()/Constants.getPixelsPerUnit());
        renderData.getDynamicItem().setPosY(Constants.getViewPortHeight() - Gdx.input.getY() / Constants.getPixelsPerUnit());

    }

    @Override
    public void mouseMoved(int x, int y) {
        //System.out.println(x + ", " + y);
        if (renderData.getDynamicItem() != null) {
            //System.out.println(x/Constants.getViewPortWidth()+", "+y/Constants.getViewPortHeight());
            renderData.getDynamicItem().setPosX(x/Constants.getPixelsPerUnit());
            System.out.println(Constants.getViewPortHeight()+"      "+y/Constants.getViewPortHeight()+"             "+y);
            renderData.getDynamicItem().setPosY(Constants.getViewPortHeight()-y/Constants.getPixelsPerUnit());
        }
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX+" "+screenY);
        renderData.getLayers()[0].getItems().add(renderData.getDynamicItem().copy());
    }
}
