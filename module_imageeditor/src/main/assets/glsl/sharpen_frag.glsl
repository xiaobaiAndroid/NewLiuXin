#version 310 es
precision mediump float;

out vec4 FragColor;

in vec2 vTexCoords;
uniform sampler2D uSourceTex;

//范围在0.0~2.0, 1.0最佳锐化效果，0.0为原图
uniform float uSharpen;

void main(){
    //卷积内核中各个元素对应像素相对于待处理像素的纹理坐标偏移量
    vec2 offset0 = vec2(-1.0f, -1.0f); vec2 offset1 = vec2(0.0f, 1.0f); vec2 offset2 = vec2(1.0f, 1.0f);
    vec2 offset3 = vec2(-1.0f, 0.0f);  vec2 centerPosition = vec2(0.0f, 0.0f); vec2 offset4 = vec2(1.0f, 0.0f);
    vec2 offset5 = vec2(-1.0, -1.0f);  vec2 offset6 = vec2(0.0f, -1.0f); vec2 offset7 = vec2(1.0f, -1.0f);

    //卷积内核的各个值
    float kernelValue0 = 0.0f; float kernelValue1 = -uSharpen; float kernelValue2 = 0.0f;
    float kernelValue3 = -uSharpen; float centerValue = 4.0f*uSharpen + 1.0f; float kernelValue4 = -uSharpen;
    float kernelValue5 = 0.0f; float kernelValue6 = -uSharpen; float kernelValue7 = 0.0f;

    vec4 temp0 = texture(uSourceTex, vTexCoords.st + offset0.xy/512.0f);
    vec4 temp1 = texture(uSourceTex, vTexCoords.st + offset1.xy/512.0f);
    vec4 temp2 = texture(uSourceTex, vTexCoords.st + offset2.xy/512.0f);
    vec4 temp3 = texture(uSourceTex, vTexCoords.st + offset3.xy/512.0f);
    vec4 tempCenter = texture(uSourceTex, vTexCoords.st + centerPosition.xy/512.0f);
    vec4 temp4 = texture(uSourceTex, vTexCoords.st + offset4.xy/512.0f);
    vec4 temp5 = texture(uSourceTex, vTexCoords.st + offset5.xy/512.0f);
    vec4 temp6 = texture(uSourceTex, vTexCoords.st + offset6.xy/512.0f);
    vec4 temp7 = texture(uSourceTex, vTexCoords.st + offset7.xy/512.0f);

    //最终的颜色和
    vec4 sum = kernelValue0*temp0 + kernelValue1*temp1 + kernelValue2*temp2 +
                kernelValue3*temp3 + centerValue*tempCenter + kernelValue4*temp4 +
                kernelValue5*temp5 + kernelValue6*temp6 + kernelValue7*temp7;


    const float scaleFactor = 1.0;//给出最终求和时的加权因子(为调整亮度)
    FragColor = sum * scaleFactor;

}