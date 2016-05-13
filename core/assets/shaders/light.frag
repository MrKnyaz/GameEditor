//SpriteBatch will use texture unit 0
uniform sampler2D u_texture;

//"in" varyings from our vertex shader
varying vec4 v_color;
varying vec2 v_texCoords;
varying int v_lightCount;
varying vec3[26] v_light;

void main() {
	//sample the texture
	vec4 texColor = texture2D(u_texture, v_texCoords);

	//invert the red, green and blue channels
	//texColor.rgb = 1.0 - texColor.rgb;
	//texColor.rgb = vec3(0,0,1);
	//if (v_lightPos.y > gl_FragCoord.y) texColor.rgb = vec3(1, 0, 0);


	vec2 pix = gl_FragCoord.xy;
	float dark_intensity = 85f;
	for (int i=0; i<v_lightCount+10; i++) {
		//calculate distance between current pixel and light center
		float light_radius = v_light[i].z;
		float distance = sqrt(pow(pix.x-v_light[i].x,2)+pow(pix.y-v_light[i].y,2));
		float light_intensity = 0.0;
		if (distance < light_radius) {
			//calculate intensity of light in percent. If distance is zero then light intensity is 100
			light_intensity = ((light_radius-distance)/light_radius)*100;
			//lower dark intensity by light intensity percentage
			if (dark_intensity > 0) {
				dark_intensity = (dark_intensity/100)*(100 - (pow(light_intensity,1+light_intensity/500)));
			} else {
				dark_intensity = (dark_intensity/100)*(100 + (pow(light_intensity,0.6)));
			}
		}
		vec3 rgbColor = (texColor.rgb-(texColor.rgb/100)*dark_intensity);
		//final color
		gl_FragColor = v_color * vec4(rgbColor, texColor.a);

	}

}