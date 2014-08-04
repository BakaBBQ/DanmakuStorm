#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;



const vec2 gaussFilter[7] = {
	-3.0,	0.015625,
	-2.0,	0.09375,
	-1.0,	0.234375,
	0.0,	0.3125,
	1.0,	0.234375,
	2.0,	0.09375,
	3.0,	0.015625
};

void main()
{
	vec4 color = 0.0;
	for( int i = 0; i < 7; i++ )
	{
		color += texture2D( u_texture, vec2( v_texCoords.x+gaussFilter[i].x, v_texCoords.y+gaussFilter[i].x ) )*gaussFilter[i].y;
	}

	gl_FragColor = color;
}