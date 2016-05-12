package net.aknyazev.game.editor.assets.shaders;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.aknyazev.game.editor.model.Layer;

/**
 * Created by MrKnyaz on 2016-04-27.
 */
public class DefaultShader extends  AbstractShader {

    private static ShaderProgram shaderProgram = SpriteBatch.createDefaultShader();

    public DefaultShader(String name) {
        super(name);
    }

    @Override
    public void apply(SpriteBatch batch, Layer layer) {
        batch.setShader(shaderProgram);
    }
}
