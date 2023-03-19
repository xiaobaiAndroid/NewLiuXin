/**
 *@author: baizf
 *@date: 2023/2/14
*/
//

#include "FiltersSampler.h"
#include "Logutils.h"

FiltersSampler::FiltersSampler(CameraProxy *camera) {
    mCamera = camera;
    mModelMatrix = ModelMatrix();

    OriginalSampler *pOriginalSampler = new OriginalSampler();
    pOriginalSampler->mCamera = camera;
    mFilterSamplers.push_back(pOriginalSampler);

    BrightnessSampler *pBrightnessSampler = new BrightnessSampler();
    pBrightnessSampler->mCamera = camera;
    mFilterSamplers.push_back(pBrightnessSampler);

    ContrastSampler *pContrastSampler = new ContrastSampler();
    pContrastSampler->mCamera = camera;
    mFilterSamplers.push_back(pContrastSampler);

    SharpenSampler *pSharpenSampler = new SharpenSampler();
    pSharpenSampler->mCamera = camera;
    mFilterSamplers.push_back(pSharpenSampler);

    SaturationSampler *pSaturationSampler = new SaturationSampler();
    pSaturationSampler->mCamera = camera;
    mFilterSamplers.push_back(pSaturationSampler);

    ExposureSampler *pExposureSampler = new ExposureSampler();
    pExposureSampler->mCamera = camera;
    mFilterSamplers.push_back(pExposureSampler);

}

void FiltersSampler::init() {
    for (auto &mFilterSampler: mFilterSamplers) {
        mFilterSampler->init();
    }
    initFlipVertex();

}

void FiltersSampler::setSourceImage(NativeImage &nativeImage, int imageScaleWidth, int imageScaleHeight) {
    mImageScaleWidth = imageScaleWidth;
    mImageScaleHeight = imageScaleHeight;

    if (mSourceTex) {
        mSourceTex->modifyImage2D(&nativeImage);
    }else{
        mSourceTex = new TextureProxy();
        mSourceTex->setImage2D(&nativeImage);
    }

    for (auto &mFilterSampler: mFilterSamplers) {
        mFilterSampler->setRenderSize(mImageScaleWidth, mImageScaleHeight);
    }
    initFrameBuffers();
}


void FiltersSampler::initFrameBuffers() {
    deleteFrameBuffers();
    if(mTextures.empty()){

        //初始化帧缓冲
        glGenFramebuffers(1, &mFrameBufferId);

        for (int i = 0; i < mFilterSamplers.size(); ++i) {

            //----初始化纹理
            auto *texture = new TextureProxy();

            NativeImage nativeImage;
            nativeImage.width = mImageScaleWidth;
            nativeImage.height = mImageScaleHeight;
            nativeImage.format = AndroidBitmapFormat::ANDROID_BITMAP_FORMAT_RGBA_8888;
            texture->setImage2D(&nativeImage, GL_REPEAT, GL_REPEAT);
            mTextures.push_back(texture);

        }
    }

}

void FiltersSampler::deleteFrameBuffers() {

    glDeleteFramebuffers(1, &mFrameBufferId);

    for (auto texture: mTextures) {
        if (texture) {
            texture->destroy();
            delete texture;
        }
    }
    mTextures.clear();
}

void FiltersSampler::initFlipVertex() {
    float vertices[] = {
            -1.0f, 1.0f, 0.0f, 0.0f, 0.0f, //左上
            -1.0f, -1.0f, 0.0f, 0.0f, 1.0f, //左下
            1.0f, 1.0f, 0.0, 1.0f, 0.0f, //右上

            1.0f, 1.0f, 0.0, 1.0f, 0.0f, //右上
            -1.0f, -1.0f, 0.0f, 0.0f, 1.0f, //左下
            1.0f, -1.0, 0.0f, 1.0f, 1.0f //右下
    };

    if (mFlipVBO) {
        mFlipVBO->destroy();
        delete mFlipVBO;
    }

    mFlipVBO = new VBOProxy(vertices, 30);

    if (mFlipVAO) {
        mFlipVAO->destroy();
        delete mFlipVAO;
    }
    mFlipVAO = new VAOProxy();
    mFlipVAO->bind();
    mFlipVAO->parseVertices(0, 3, 5, 0);
    mFlipVAO->parseVertices(1, 2, 5, 3);
    mFlipVAO->unbind();
}


void FiltersSampler::draw(VAOProxy &vao) {
    if(mTextures.empty() || !mSourceTex){
        return;
    }
    mModelMatrix.scale = glm::vec3(1.0f);

    CoordinateSystems::setViewport(mImageScaleWidth, mImageScaleHeight);

    glBindFramebuffer(GL_FRAMEBUFFER, mFrameBufferId);


    //离屏渲染
    //先渲染滤镜纹理，再把渲染好的滤镜纹理依次传给亮度采样器渲染。依次循环叠加效果
    auto *previewTexture = mSourceTex;


    for (int i = 0; i < mTextures.size(); ++i) {
        TextureProxy *canvas = mTextures[i];
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D,
                               canvas->getId(), 0);

        ProgramProxy::clearColor();
        ProgramProxy::clearBuffer();

        mModelMatrix.scale = glm::vec3(1.0f);
        mFilterSamplers.at(i)->draw(previewTexture, &vao, mModelMatrix);
        previewTexture = canvas;
    }
}

void FiltersSampler::setFilter(int filter_type) {
    BaseFilterSampler *filterSampler = nullptr;
    if (filter_type == 1) {
        filterSampler = new FairyTaleSampler();
    } else if (filter_type == 2) {
        filterSampler = new SunriseSampler();
    } else if (filter_type == 3) {
        filterSampler = new SunsetSampler();
    } else if (filter_type == 4) {
        filterSampler = new WhiteCatSampler();
    } else if (filter_type == 5) {
        filterSampler = new BlackCatSampler();
    } else if (filter_type == 6) {
        filterSampler = new SkinWhitenSampler();
    } else if (filter_type == 7) {
        filterSampler = new HealthySampler();
    } else if (filter_type == 8) {
        filterSampler = new SweetsSampler();
    } else if (filter_type == 9) {
        filterSampler = new RomanceSampler();
    } else if (filter_type == 10) {
        filterSampler = new SakuraSampler();
    } else if (filter_type == 11) {
        filterSampler = new WarmSampler();
    } else if (filter_type == 12) {
        filterSampler = new AntiqueSampler();
    } else if (filter_type == 13) {
        filterSampler = new NostalgiaSampler();
    } else if (filter_type == 14) {
        filterSampler = new CalmSampler();
    } else if (filter_type == 15) {
        filterSampler = new LatteSampler();
    } else if (filter_type == 16) {
        filterSampler = new TenderSampler();
    } else if (filter_type == 17) {
        filterSampler = new CoolSampler();
    } else if (filter_type == 18) {
        filterSampler = new EmeraldSampler();
    } else if (filter_type == 19) {
        filterSampler = new EvergreenSampler();
    } else if (filter_type == 20) {
        filterSampler = new CrayonSampler();
    } else if (filter_type == 21) {
        filterSampler = new SketchSampler();
    } else {
        filterSampler = new OriginalSampler();
    }

    mFilterSamplers.erase(mFilterSamplers.begin());
    if (mCurrentFilter) {
        mCurrentFilter->releaseGL();
        delete mCurrentFilter;
        mCurrentFilter = nullptr;
    }
    filterSampler->mCamera = mCamera;
    filterSampler->init();
    filterSampler->setRenderSize(mImageScaleWidth, mImageScaleHeight);

    mFilterSamplers.insert(mFilterSamplers.begin(), filterSampler);
    mCurrentFilter = filterSampler;

}

float FiltersSampler::getAdjustValue(const int adjustType) {
    auto adjustSampler = (AdjustSampler *) mFilterSamplers.at(adjustType);
    return adjustSampler->getValue();
}

void FiltersSampler::setAdjustValue(const int adjustType,float value) {
    auto adjustSampler = (AdjustSampler *) mFilterSamplers.at(adjustType);
    adjustSampler->setValue(value);
}


BlendSampler *FiltersSampler::getFilterSampler() {
    return dynamic_cast<BlendSampler *>(mCurrentFilter);
}


void FiltersSampler::releaseGL() {
    if(mFlipVBO){
        mFlipVBO->destroy();
        delete mFlipVBO;
        mFlipVBO = nullptr;
    }
    if(mFlipVAO){
        mFlipVAO->destroy();
        delete mFlipVAO;
        mFlipVAO = nullptr;
    }

    for (auto filterSampler: mFilterSamplers) {
        if (filterSampler) {
            filterSampler->releaseGL();
            delete filterSampler;
        }
    }
    mFilterSamplers.clear();

    if (mSourceTex) {
        mSourceTex->destroy();
        delete mSourceTex;
        mSourceTex = nullptr;
    }
    deleteFrameBuffers();

}

TextureProxy* FiltersSampler::getBlendTexture() {
//    if(mTextures.empty()){
//        return mSourceTex;
//    }else{
//        return mTextures.back();
//    }
    return mSourceTex;
}
