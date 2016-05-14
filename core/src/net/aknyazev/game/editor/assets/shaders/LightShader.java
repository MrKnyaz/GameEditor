package net.aknyazev.game.editor.assets.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import net.aknyazev.game.editor.Constants;
import net.aknyazev.game.editor.model.Layer;
import net.aknyazev.game.editor.model.LightObject;
import net.aknyazev.game.editor.world.RenderData;

import java.util.List;

/**
 * Created by MrKnyaz on 2016-04-27.
 */
public class LightShader extends  AbstractShader {

    private static String vertexShader = Gdx.files.internal("shaders/light.vert").readString();
    private static String fragmentShader = Gdx.files.internal("shaders/light.frag").readString();
    private static ShaderProgram shaderProgram = new ShaderProgram(vertexShader, fragmentShader);

    public LightShader(String name) {
        super(name);
    }

    @Override
    public void apply(SpriteBatch batch, RenderData renderData, int layerIndex) {
        batch.setShader(shaderProgram);
        Layer layer = renderData.getLayers().get(layerIndex);
        List<LightObject> lights = layer.getLights();
        int lightsCount = lights.size();
        //generating float array, split by blocks(xpos, ypos, radius, angle)
        float[] lightsArray = new float[(lights.size()+1)*3];
        int index = 0;
        for (LightObject light: lights) {
            setValues(lightsArray, index, light);
            index+=3;
        }
        if (layerIndex == renderData.getCurrentLayerIndex() && renderData.getDynamicItem() instanceof LightObject) {
            setValues(lightsArray, index, (LightObject)  renderData.getDynamicItem());
            lightsCount++;
        }
        shaderProgram.setUniformf("u_lightProps", new Vector2(lightsCount, renderData.getDarkIntensity()));
        shaderProgram.setUniform3fv("u_light", lightsArray, 0, lightsCount * 3);
    }

    private void setValues(float[] lightsArray, int index, LightObject light) {
        OrthographicCamera camera = Constants.cam;
        lightsArray[index] = (light.getPosX() - (camera.position.x-camera.viewportWidth/2))*Constants.getPixelsPerUnit();
        lightsArray[index+1] = (light.getPosY() - (camera.position.y-camera.viewportHeight/2))*Constants.getPixelsPerUnit();
        lightsArray[index+2] = light.getLength()*Constants.getPixelsPerUnit();
        //lightsArray[index+3] = light.getRotation();
    }
}
