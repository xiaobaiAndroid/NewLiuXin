#version 310 es

precision mediump float;

out vec4 FragColor;

uniform sampler2D uSourceTex;

in vec2 vTexCoords;

void main(){
    FragColor = texture(uSourceTex,vTexCoords);
}