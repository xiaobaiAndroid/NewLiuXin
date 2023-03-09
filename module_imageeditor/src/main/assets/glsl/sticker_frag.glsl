#version 310 es
precision mediump float;

out vec4 FragColor;

uniform sampler2D uSourceTex;

in vec2 vTexCoords;

void main(){
    vec4 textColor = texture(uSourceTex, vTexCoords);

    float gamma = 2.2f;
    vec3 value = pow(textColor.rgb, vec3(1.0/gamma));
    FragColor = vec4(value, textColor.a);
}