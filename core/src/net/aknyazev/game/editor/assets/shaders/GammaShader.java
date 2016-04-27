package net.aknyazev.game.editor.assets.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.aknyazev.game.editor.world.RenderData;

/**
 * Created by MrKnyaz on 2016-04-27.
 */
public class GammaShader extends  AbstractShader {

    private static String vertexShader = Gdx.files.internal("shaders/gamma.vert").readString();
    private static String fragmentShader = Gdx.files.internal("shaders/gamma.frag").readString();
    private static ShaderProgram shaderProgram = new ShaderProgram(vertexShader, fragmentShader);

    //100 is normal, less is darker, more lighter
    float gamma =100;

    public GammaShader(String name, float gamma) {
        super(name);
        this.gamma = gamma;
    }

    @Override
    public void apply(SpriteBatch batch, RenderData renderData) {
        batch.setShader(shaderProgram);
        shaderProgram.setUniformf("u_gamma", gamma);
    }
}
