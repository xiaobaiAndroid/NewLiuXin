#version 310 es
precision mediump float;

out vec4 FragColor;

in vec2 vTexCoords;

uniform sampler2D uSourceTex;

//对比度 0.6~1.4
uniform float uContrast;

void main(){
    vec4 textureColor = texture(uSourceTex, vTexCoords);
    FragColor = vec4(((textureColor.rgb - vec3(0.5f)) * uContrast + vec3(0.5f)), textureColor.w);
}