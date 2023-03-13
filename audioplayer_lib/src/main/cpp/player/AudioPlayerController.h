/**
 *@author: baizf
 *@date: 2023/3/10
*/
//

#ifndef NEWLIUXIN_AUDIOPLAYERCONTROLLER_H
#define NEWLIUXIN_AUDIOPLAYERCONTROLLER_H

#include <string>
#include "decode/AudioDecoder.h"
#include "play/AudioPlayer.h"

using namespace std;


class AudioPlayerController{
private:
    AudioPlayer *mPlayer = nullptr;
    AudioDecoder *mDecoder = nullptr;

   static void onDecodeReadyCallback(void *obj, int samplerRate, int channelCount);
   static void onDecodeDataCallback(void *obj, uint8_t *data, int numFrames);


public:
    AudioPlayerController();

    void decodeReadyCallback(int samplerRate, int channelCount);
    void prepare(const string &url, int mAudioOutputDeviceId);
    void play();
    void stop();
    void release();
    ~AudioPlayerController();


    void decodeDataCallback(uint8_t *data, int numFrames);
};

#endif //NEWLIUXIN_AUDIOPLAYERCONTROLLER_H
