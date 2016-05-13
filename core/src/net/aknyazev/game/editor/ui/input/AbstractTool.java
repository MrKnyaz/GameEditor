package net.aknyazev.game.editor.ui.input;


import net.aknyazev.game.editor.model.AbstractGameObject;
import net.aknyazev.game.editor.ui.command.Command;
import net.aknyazev.game.editor.world.RenderData;
import net.aknyazev.game.editor.ui.UI;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
abstract public class AbstractTool {
    protected RenderData renderData;
    protected UI ui;

    public AbstractTool(RenderData renderData, UI ui) {
        this.renderData = renderData;
        this.ui = ui;
    }

    public void init() {}

    public void changeItem(int amount) {}

    public Command rotate(int amount) {return null;}
    public Command scale(float amount) {
        AbstractGameObject item = renderData.getDynamicItem();
        float scaleX = item.getScaleX();
        float scaleY = item.getScaleY();
        if (scaleX + amount > 0) {
            scaleX += amount;
            item.setScaleX(scaleX);
        }
        if (scaleY + amount > 0) {
            scaleY += amount;
            item.setScaleY(scaleY);
        }
        return null;
    }
    public Command scaleX(int amount) {return null;}
    public Command scaleY(int amount) {return null;}
    public Command opacity(float amount) {
        AbstractGameObject item = renderData.getDynamicItem();
        item.setOpacity(item.getOpacity()+amount);
        return null;
    }

    public Command flipX() {
        if (renderData.getDynamicItem().isFlipX()) {
            renderData.getDynamicItem().setFlipX(false);
        } else {
            renderData.getDynamicItem().setFlipX(true);
        }
        return null;
    }

    public Command flipY() {
        if (renderData.getDynamicItem().isFlipY()) {
            renderData.getDynamicItem().setFlipY(false);
        } else {
            renderData.getDynamicItem().setFlipY(true);
        }
        return null;
    }

    public void attachToMouse(int x, int y) {
        if (renderData.getDynamicItem() != null) {
            renderData.getDynamicItem().setPosX(renderData.getWorldX(x));
            renderData.getDynamicItem().setPosY(renderData.getWorldY(y));
        }
    }


    public Command submit(int screenX, int screenY) {
        //To change body of implemented methods use File | Settings | File Templates.
        return null;
    }

    public void cancel(int screenX, int screenY) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Command customKeyAction() {
        return null;
    }

    public RenderData getRenderData() {
        return renderData;
    }
}
