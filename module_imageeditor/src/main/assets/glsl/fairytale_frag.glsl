#version 310 es
precision mediump float;

out vec4 FragColor;

uniform sampler2D uSourceTex;
uniform sampler2D uFairytaleTex;

in vec2 vTexCoords;

void main(){
    lowp vec4 textureColor = texture(uSourceTex, vTexCoords);

    mediump float blueColor = textureColor.b * 63.0f;
    mediump vec2 quad1;
    quad1.y = floor(floor(blueColor) / 8.0f);
    quad1.x = floor(blueColor) - (quad1.y * 8.0f);

    mediump vec2 quad2;
    quad2.y = floor(ceil(blueColor) / 8.0f);
    quad2.x = ceil(blueColor) - (quad2.y * 8.0f);

    highp vec2 texPos1;
    texPos1.x = (quad1.x * 0.125f) + 0.5f/512.0f + ((0.125f - 1.0f/512.0f) * textureColor.r);
    texPos1.y = (quad1.y * 0.125f) + 0.5f/512.0f + ((0.125f - 1.0f/512.0f) * textureColor.g);

    highp vec2 texPos2;
    texPos2.x = (quad2.x * 0.125f) + 0.5f/512.0f + ((0.125f - 1.0/512.0f) * textureColor.r);
    texPos2.y = (quad2.y * 0.125f) + 0.5f/512.0f+ ((0.125f - 1.0f/512.0f) * textureColor.g);

    lowp vec4 newColor1 = texture(uFairytaleTex, texPos1);
    lowp vec4 newColor2 = texture(uFairytaleTex, texPos2);

    lowp vec4 newColor = mix(newColor1, newColor2, fract(blueColor));

    FragColor = vec4(newColor.rgb, textureColor.w);
}