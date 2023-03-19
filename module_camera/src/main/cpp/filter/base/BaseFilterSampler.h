/**
 * 滤镜采样器基类
 *@author: baizf
 *@date: 2023/2/2
*/
//

#ifndef NATIVEIMAGEEDITOR_BASEFILTERSAMPLER_H
#define NATIVEIMAGEEDITOR_BASEFILTERSAMPLER_H

#include "base/BaseSampler.h"
#include "NativeImage.h"
#include <android/bitmap.h>

static string DEFAULT_VERTEX_SRC = "#version 310 es\n"
                            "\n"
                            "layout(location = 0) in vec3 aPos;\n"
                            "layout(location = 1) in vec2 aTexCoords;\n"
                            "\n"
                            "out vec2 vTexCoords;\n"
                            "uniform mat4 uModel;\n"
                            "\n"
                            "void main(){\n"
                            "    gl_Position = uModel * vec4(aPos, 1.0f);\n"
                            "    vTexCoords = aTexCoords;\n"
                            "}";

static string DEFAULT_FRAG_SRC =  "#version 310 es\n"
                           "precision mediump float;\n"
                           "\n"
                           "out vec4 FragColor;\n"
                           "uniform sampler2D uSourceTex;\n"
                           "in vec2 vTexCoords;\n"
                           "\n"
                           "void main(){\n"
                           "    FragColor = texture(uSourceTex,vTexCoords);\n"
                           "}";

class BaseFilterSampler: public  BaseSampler{

protected:

    string mVertexSrc = DEFAULT_VERTEX_SRC;

    string mFragSrc = DEFAULT_FRAG_SRC;

    ProgramProxy *mProgramProxy = nullptr;

    int mVerticesCount = 6;

    virtual string getVertexSrc();
    virtual string getFragSrc();
public:
    virtual void init();



    virtual void draw(TextureProxy *textureProxy, VAOProxy *cVAO, ModelMatrix modelMatrix);
    virtual void drawBefore();

    virtual void releaseGL();

    virtual ~BaseFilterSampler();
};

#endif //NATIVEIMAGEEDITOR_BASEFILTERSAMPLER_H
