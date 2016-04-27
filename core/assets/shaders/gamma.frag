//SpriteBatch will use texture unit 0
uniform sampler2D u_texture;

//"in" varyings from our vertex shader
varying vec4 v_color;
varying vec2 v_texCoords;
varying float v_gamma;

void main() {
	//sample the texture
	vec4 texColor = texture2D(u_texture, v_texCoords);
	
	vec3 rgbColor = (texColor.rgb/100f)*v_gamma;//vec3(0.5f, 0.5f, v_gamma/10f);

	//final color
	gl_FragColor = v_color * vec4(rgbColor, texColor.a);
}