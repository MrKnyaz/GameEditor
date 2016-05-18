package net.aknyazev.game.editor.ui.input;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.aknyazev.game.editor.assets.AssetManager;
import net.aknyazev.game.editor.model.AnimationObject;
import net.aknyazev.game.editor.model.SpriteObject;
import net.aknyazev.game.editor.ui.UI;
import net.aknyazev.game.editor.ui.command.Command;
import net.aknyazev.game.editor.world.RenderData;

/**
 * User: MrKnyaz
 * Date: 1/3/14
 */
public class AnimationTool extends AbstractTool {

    private int mode = 0;
    private AnimationObject animation;

    public AnimationTool(RenderData renderData, UI ui) {
        super(renderData, ui);
    }

    @Override
    public void init() {
        ui.nextSprite();
    }

    @Override
    public void changeItem(int amount) {
        if (amount > 0) {
            if (mode == 1) {
                ui.nextSprite();
            } else {
                ui.nextAnimation();
            }
        } else {
            if (mode == 1) {
                ui.previousSprite();
            } else {
                ui.previousAnimation();
            }
        }
        attachToMouse(Gdx.input.getX(), Gdx.input.getY());
    }

    public Command submit(int screenX, int screenY) {
        if (mode == 0) {
            return renderData.addItem(new AnimationObject((AnimationObject)renderData.getDynamicItem()));
        } else {
            animation.addFrame(new SpriteObject((SpriteObject)renderData.getDynamicItem()));
        }
        return null;
    }

    @Override
    public Command customKeyAction() {
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            if (mode == 0) {
                mode = 1;
                animation = new AnimationObject(Integer.toString(AssetManager.getInstance().getAnimations().size()+1));
            } else {
                mode = 0;
                if (animation.size() > 0) {
                    AssetManager.getInstance().getAnimations().add(animation);
                    ui.updateAnimationList();
                }
            }
        }
        return null;
    }
}
