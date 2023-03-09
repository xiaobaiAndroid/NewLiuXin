#version 310 es
precision mediump float;

out vec4 FragColor;

in vec2 vTexCoords;

uniform sampler2D uSourceTex;

//范围：0.0~2.0  1.0输出原图， 0.0输出灰度图 2.0饱和度最大
uniform float uSaturation;

const vec3 luminanceWeighting = vec3(0.2125f, 0.7154f, 0.0721f);

void main(){
    vec4 textureColor = texture(uSourceTex, vTexCoords);
    float luminance = dot(textureColor.rgb, luminanceWeighting);
    vec3 greyScaleColor = vec3(luminance);

    FragColor = vec4(mix(greyScaleColor,textureColor.rgb, uSaturation),textureColor.w);
}