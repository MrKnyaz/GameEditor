package net.aknyazev.game.editor.assets.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.aknyazev.game.editor.model.Layer;

/**
 * Created by MrKnyaz on 2016-04-27.
 */
public class LightShader extends  AbstractShader {

    private static String vertexShader = Gdx.files.internal("shaders/gamma.vert").readString();
    private static String fragmentShader = Gdx.files.internal("shaders/gamma.frag").readString();
    private static ShaderProgram shaderProgram = new ShaderProgram(vertexShader, fragmentShader);

    //100 is normal, less is darker, more lighter
    float gamma =100;

    public LightShader(String name, float gamma) {
        super(name);
        this.gamma = gamma;
    }

    @Override
    public void apply(SpriteBatch batch, Layer layer) {
        batch.setShader(shaderProgram);
        shaderProgram.setUniformf("u_gamma", gamma);
    }
}
