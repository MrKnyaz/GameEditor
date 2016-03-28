package net.aknyazev.game.editor.ui.control;

import com.badlogic.gdx.InputProcessor;
import net.aknyazev.game.editor.model.RenderData;
import net.aknyazev.game.editor.model.UIData;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
public class MapInputProcessor implements InputProcessor {
    private AbstractControlState state;

    public MapInputProcessor(AbstractControlState state) {
        this.state = state;
    }

    @Override
    public boolean keyDown(int i) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean keyUp(int i) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean keyTyped(char c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX+" "+screenY);
        state.touchDown(screenX, screenY, pointer, button);
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        state.mouseMoved(x, y);
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean scrolled(int i) {
        state.scrolled(i);
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setState(AbstractControlState state) {
        this.state = state;
    }
    //For changing buttons view
    public AbstractControlState getState() {
        return state;
    }
}

