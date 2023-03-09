#version 310 es
precision mediump float;

out vec4 FragColor;

in vec2 vTexCoords;
uniform sampler2D uSourceTex;

//-1.0 ~ 1.0  0.0输出原图
uniform float uExposure;

void main(){
    vec4 textureColor = texture(uSourceTex, vTexCoords);
    FragColor = vec4(textureColor.rgb * pow(2.0f, uExposure), textureColor.w);
}