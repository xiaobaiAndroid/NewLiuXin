/**
 *@author: baizf
 *@date: 2023/3/2
*/
//

#ifndef NATIVEVIDEOPLAYER_FFMPEGERRORPROXY_H
#define NATIVEVIDEOPLAYER_FFMPEGERRORPROXY_H

#include "Logutils.h"
#include "libavutil/error.h"
#include <string>

using namespace std;

class FFmpegErrorProxy {
public:
    static bool hasError(const int result) {
        bool hasError = true;
        char *message = av_err2str(result);
        switch (result) {
            case AVERROR(EAGAIN):
                LOGE("%s", message);
                break;
            case AVERROR(EINVAL):
                LOGE("%s", message);
                break;
            case AVERROR(ENOMEM):
                LOGE("%s", message);
                break;
            case AVERROR_EOF:
                LOGE("%s", message);
                break;
            case 0:
                hasError = false;
                break;
            default:
                LOGE("%s", message);
                break;
        }
        return hasError;
    }

    static void printErrorMessage(const int result, const string tag = "") {
//        char message[512];
//
//        av_strerror(result, message, 512);
        LOGE("%s %s", tag.c_str(), av_err2str(result));
    }
};

#endif //NATIVEVIDEOPLAYER_FFMPEGERRORPROXY_H
