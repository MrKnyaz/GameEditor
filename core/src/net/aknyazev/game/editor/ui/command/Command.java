package net.aknyazev.game.editor.ui.command;

/**
 * Created by MrKnyaz on 2016-04-15.
 */
abstract public class Command {

    int DONE = 0;
    int UNDONE = 0;

    int current = -1;

    public Command execute() {
        runCommand();
        current = DONE;
        return this;
    }

    public Command undo() {
        if (current == DONE) {
            undoCommand();
            current = UNDONE;
        }
        return this;
    }

    public Command redo() {
        if (current == UNDONE) {
            redoCommand();
            current = DONE;
        }
        return this;
    }

    abstract protected void runCommand();
    abstract protected void undoCommand();
    abstract protected void redoCommand();
}
