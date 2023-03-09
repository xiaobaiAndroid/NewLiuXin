#version 310 es
precision mediump float;

out vec4 FragColor;

in vec2 vTexCoords;

uniform sampler2D uSourceTex;
uniform vec2 singleStepOffset;
uniform float strength;

const highp vec3 W = vec3(0.299,0.587,0.114);


void main()
{
    float threshold = 0.0;
    //pic1
    vec4 oralColor = texture(uSourceTex, vTexCoords);

    //pic2
    vec3 maxValue = vec3(0.0,0.0,0.0);

    for(int i = -2; i<=2; i++)
    {
        for(int j = -2; j<=2; j++)
        {
            vec4 tempColor = texture(uSourceTex, vTexCoords+singleStepOffset*vec2(i,j));
            maxValue.r = max(maxValue.r,tempColor.r);
            maxValue.g = max(maxValue.g,tempColor.g);
            maxValue.b = max(maxValue.b,tempColor.b);
            threshold += dot(tempColor.rgb, W);
        }
    }
    //pic3
    float gray1 = dot(oralColor.rgb, W);

    //pic4
    float gray2 = dot(maxValue, W);

    //pic5
    float contour = gray1 / gray2;

    threshold = threshold / 25.;
    float alpha = max(1.0,gray1>threshold?1.0:(gray1/threshold));

    float result = contour * alpha + (1.0-alpha)*gray1;

    FragColor = vec4(vec3(result,result,result), oralColor.w);
}