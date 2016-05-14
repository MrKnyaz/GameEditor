//"in" attributes from our SpriteBatch
attribute vec4 a_position;
attribute vec2 a_texCoord0;
attribute vec4 a_color;

//combined projection and view matrix
uniform mat4 u_projTrans;
//lights
uniform vec2 u_lightProps;
uniform vec3[26] u_light;


//"out" varyings to our fragment shader
varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_lightProps;
varying vec3[26] v_light;

void main() {
	v_color = a_color;
	v_color.a = v_color.a * (255.0/254.0);
	v_texCoords = a_texCoord0;
	v_lightProps = u_lightProps;
	v_light = u_light;
	gl_Position = u_projTrans * a_position;//vec4(Position.x, Position.y+35*sin(Position.x) , 0.0, 1.0);
}