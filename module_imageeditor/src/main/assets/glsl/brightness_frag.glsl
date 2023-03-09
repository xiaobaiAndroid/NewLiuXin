#version 310 es
precision mediump float;

out vec4 FragColor;

in vec2 vTexCoords;

uniform sampler2D uSourceTex;

//亮度 ~0.5f~0.5f
uniform float uBrightness;

void main(){
    vec4 textureColor = texture(uSourceTex, vTexCoords);
    FragColor = vec4(textureColor.rgb + vec3(uBrightness), textureColor.w);
}