/**
 *@author: baizf
 *@date: 2023/2/25
*/
//

#ifndef NATIVEVIDEOPLAYER_TIMEUTILS_H
#define NATIVEVIDEOPLAYER_TIMEUTILS_H

#include <sys/time.h>

class TimeUtils{
public:
    static long getCurrentTime(){
        struct timeval tv;
        gettimeofday(&tv,NULL);
        return tv.tv_sec * 1000 + tv.tv_usec / 1000;
    }

    static  long long currentTimeMills(){
        struct timeval tv;
        gettimeofday(&tv, NULL);

        return (long long)tv.tv_sec * 1000 + tv.tv_usec / 1000;
    }

    static long getCurrentTimeSecSinceReferenceDate()
    {
        struct timeval tv;
        gettimeofday(&tv,NULL);
        return tv.tv_sec;
    }
};

#endif //NATIVEVIDEOPLAYER_TIMEUTILS_H
