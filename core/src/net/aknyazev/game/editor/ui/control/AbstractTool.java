package net.aknyazev.game.editor.ui.control;


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

    public void changeItem(int amount) {}

    public Command rotate(int amount) {return null;}
    public Command scale(int amount) {return null;}
    public Command scaleX(int amount) {return null;}
    public Command scaleY(int amount) {return null;}

    public void attachToMouse(int x, int y) {}

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
}
