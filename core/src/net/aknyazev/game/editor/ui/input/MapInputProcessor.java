package net.aknyazev.game.editor.ui.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import net.aknyazev.game.editor.ui.command.Command;
import net.aknyazev.game.editor.world.RenderData;

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
            } else if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyPressed(Input.Keys.TAB)) {
                state.flipY();
            } else if(Gdx.input.isKeyPressed(Input.Keys.TAB)) {
                state.flipX();
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
        try {
            Command command = state.submit(screenX, screenY);
            if (command != null) {
                undoList.push(command);
            }
        } catch(Exception e) {
            e.printStackTrace();
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
        try {
            state.attachToMouse(x, y);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean scrolled(int i) {
        try {
            if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) {
                state.scale(i*0.1f);
            } else if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                state.rotate(i);
            } else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                state.opacity(i*0.05f);
            } else {
                state.changeItem(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setState(AbstractTool state) {
        this.state = state;
        state.getRenderData().setDynamicItem(null);
    }
    //For changing buttons view
    public AbstractTool getState() {
        return state;
    }
}

