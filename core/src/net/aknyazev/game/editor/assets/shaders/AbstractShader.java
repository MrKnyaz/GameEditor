package net.aknyazev.game.editor.assets.shaders;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.aknyazev.game.editor.world.RenderData;

/**
 * Created by MrKnyaz on 2016-04-27.
 */
abstract public class AbstractShader {

    private String name;

    public AbstractShader(String name) {
        this.name = name;
    }

    public void apply(SpriteBatch batch, RenderData renderData) {
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractShader that = (AbstractShader) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
