package net.aknyazev.game.editor.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrKnyaz on 2016-05-18.
 */
public class AnimationObject extends AbstractGameObject {

    private int delay = 80;
    private int totalTime = 0;
    private List<SpriteObject> sprites = new ArrayList<SpriteObject>();

    public AnimationObject(String name) {
        this.name = name;
    }

    public AnimationObject(AnimationObject animationObject) {
        super(animationObject);
        for (SpriteObject sprite: animationObject.sprites) {
            sprites.add(sprite);
        }
    }

    public int size() {
        return sprites.size();
    }

    public void addFrame(SpriteObject sprite) {
        sprites.add(sprite);
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {
        if (sprites.size() > 0) {
            totalTime += (int)(delta*1000);
            if (totalTime >= delay*sprites.size()) {
                totalTime = totalTime % delay;
            }
            SpriteObject sprite = sprites.get(totalTime / delay);
            sprite.setFlipX(flipX);
            sprite.setFlipY(flipY);
            sprite.setScaleX(scaleX);
            sprite.setScaleY(scaleY);
            sprite.setOpacity(opacity);
            sprite.setRotation(rotation);
            sprite.setPosX(posX);
            sprite.setPosY(posY);
            sprite.draw(batch, delta);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
