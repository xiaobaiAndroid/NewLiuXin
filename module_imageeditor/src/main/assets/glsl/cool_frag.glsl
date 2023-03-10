#version 310 es

precision mediump float;

out vec4 FragColor;

in vec2 vTexCoords;

uniform sampler2D uSourceTex;
uniform sampler2D curve;

void main()
{
    lowp vec4 textureColor;
    lowp vec4 textureColorOri;

    float xCoordinate = vTexCoords.x;
    float yCoordinate = vTexCoords.y;

    highp float redCurveValue;
    highp float greenCurveValue;
    highp float blueCurveValue;

    textureColor = texture(uSourceTex, vec2(xCoordinate, yCoordinate));
    textureColorOri = textureColor;
    // step1 curve
    redCurveValue = texture(curve, vec2(textureColor.r, 0.0)).r;
    greenCurveValue = texture(curve, vec2(textureColor.g, 0.0)).g;
    blueCurveValue = texture(curve, vec2(textureColor.b, 0.0)).b;
    // step2 level
    redCurveValue = texture(curve, vec2(redCurveValue, 0.0)).a;
    greenCurveValue = texture(curve, vec2(greenCurveValue, 0.0)).a;
    blueCurveValue = texture(curve, vec2(blueCurveValue, 0.0)).a;
    // step3 brightness/constrast adjust
    redCurveValue = redCurveValue * 1.25 - 0.12549;
    greenCurveValue = greenCurveValue * 1.25 - 0.12549;
    blueCurveValue = blueCurveValue * 1.25 - 0.12549;
    //redCurveValue = (((redCurveValue) > (1.0)) ? (1.0) : (((redCurveValue) < (0.0)) ? (0.0) : (redCurveValue)));
    //greenCurveValue = (((greenCurveValue) > (1.0)) ? (1.0) : (((greenCurveValue) < (0.0)) ? (0.0) : (greenCurveValue)));
    //blueCurveValue = (((blueCurveValue) > (1.0)) ? (1.0) : (((blueCurveValue) < (0.0)) ? (0.0) : (blueCurveValue)));
    // step4 normal blending with original
    textureColor = vec4(redCurveValue, greenCurveValue, blueCurveValue, 1.0);
    textureColor = (textureColorOri - textureColor) * 0.549 + textureColor;

    FragColor = vec4(textureColor.r, textureColor.g, textureColor.b, 1.0);
}
