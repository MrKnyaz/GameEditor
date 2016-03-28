package net.aknyazev.game.editor.ui.control;


import net.aknyazev.game.editor.model.RenderData;
import net.aknyazev.game.editor.model.UIData;
import net.aknyazev.game.editor.ui.UI;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
abstract public class AbstractControlState {
    protected RenderData renderData;
    protected UI ui;

    public AbstractControlState(RenderData renderData, UI ui) {
        this.renderData = renderData;
        this.ui = ui;
    }

    abstract public void scrolled(int amount);

    abstract public void mouseMoved(int x, int y);

    public void touchDown(int screenX, int screenY, int pointer, int button) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
