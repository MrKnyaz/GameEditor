//"in" attributes from our SpriteBatch
attribute vec4 a_position;
attribute vec2 a_texCoord0;
attribute vec4 a_color;

//combined projection and view matrix
uniform mat4 u_projTrans;
uniform float u_gamma;

//"out" varyings to our fragment shader
varying vec4 v_color;
varying vec2 v_texCoords;
varying float v_gamma;

void main() {
	v_color = a_color;
	v_color.a = v_color.a * (255.0/254.0);
	v_texCoords = a_texCoord0;
	v_gamma = u_gamma;
	gl_Position = u_projTrans * a_position;//vec4(Position.x, Position.y+35*sin(Position.x) , 0.0, 1.0);
}