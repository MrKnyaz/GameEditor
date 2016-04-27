package net.aknyazev.game.editor.assets.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.aknyazev.game.editor.world.RenderData;

/**
 * Created by MrKnyaz on 2016-04-27.
 */
public class DefaultShader extends  AbstractShader {

    private static ShaderProgram shaderProgram = SpriteBatch.createDefaultShader();

    public DefaultShader(String name) {
        super(name);
    }

    @Override
    public void apply(SpriteBatch batch, RenderData renderData) {
        batch.setShader(shaderProgram);
    }
}
