package net.aknyazev.game.editor.ui.command;

import net.aknyazev.game.editor.model.AbstractGameObject;
import net.aknyazev.game.editor.model.Layer;

/**
 * Created by MrKnyaz on 2016-04-18.
 */
public class AddCommand extends Command {

    AbstractGameObject item;
    Layer layer;

    public AddCommand(AbstractGameObject item, Layer layer) {
        this.item = item;
        this.layer = layer;
    }

    @Override
    protected void runCommand() {
        layer.addItem(item);
    }

    @Override
    protected void undoCommand() {
        layer.removeItem(item);
    }

    @Override
    protected void redoCommand() {
        runCommand();
    }
}