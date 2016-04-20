package net.aknyazev.game.editor.ui.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import net.aknyazev.game.editor.Constants;
import net.aknyazev.game.editor.ui.command.Command;

import java.util.LinkedList;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
public class MapInputProcessor implements InputProcessor {
    private AbstractTool state;
    private LinkedList<Command> undoList = new LinkedList<Command>();
    private LinkedList<Command> redoList = new LinkedList<Command>();

    public MapInputProcessor(AbstractTool state) {
        this.state = state;
    }

    @Override
    public boolean keyDown(int key) {
        try {
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Input.Keys.Z)
                    && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                Command command = redoList.pop();
                if (command != null) {
                    command.redo();
                    undoList.push(command);
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Input.Keys.Z)) {
                Command command = undoList.pop();
                if (command != null) {
                    command.undo();
                    redoList.push(command);
                }
            } else {
                state.customKeyAction();
            }
        } catch (Exception e) {

        }
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
        System.out.println(screenX + " " + screenY);
        Command command = state.submit(screenX, screenY);
        if (command != null) {
            undoList.push(command);
        }
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
        state.attachToMouse(x, y);
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean scrolled(int i) {
        if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) {
            state.scale(i*0.1f);
        } else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            state.rotate(i);
        } else {
            state.changeItem(i);
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setState(AbstractTool state) {
        this.state = state;
    }
    //For changing buttons view
    public AbstractTool getState() {
        return state;
    }
}

