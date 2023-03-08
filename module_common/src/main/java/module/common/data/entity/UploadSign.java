package module.common.data.entity;

/**
 * @describe: 文件上传的临时秘钥
 * @date: 2020/3/13
 * @author: Mr Bai
 */
public class UploadSign {

    /**
     * tmpSecretId : AKIDgmA0KxHFYz3s1oEHQYUDZSL_yFwiJwbU4szlC4Ol7VPWtPJaEr2DOoHq3rlaAPyw
     * tmpSecretKey : cj5BGiP/R09UEAvJluNGjkngfwlM3kAsCwbr2lBvEmk=
     * sessionToken : pjxtPI797q4urAJonbuJzmnb8IRlzLkfd2357a13cdb6e5e6fd2c8eb9c30c14b4Gj2tJ45ksjI_Jtt1gDbvL-8X1M2WrjjLGZ2MeUw5JYcEGaZUAcMzgo_HYlRPUE-Czl91RtJPBNgKbBYOFlrkkVNVlxInYQKXqQvwJ2oGfRWgc3KjZ7mx-XyZN7VdIv6X54Lx8G9KibSTuGeXedETVknWWEDUC2mkf7qV42e-1fY7EywJOH5_dyKpySDakKClEd4YFiScGp19VBqpioLB56xn8yzoX-oQQ6qRs8EIWhtoxUnIigEyG4wJYRSVFI0Ebjt_t9nIHdeGyacAm1PLXNwvdyV_E3dTnrZfuKqPJrOvqmDub776c1moF5WOOm88187rSacWQZ98vD2_bpq0GJi-L5Ve9--k0AgFjnwaNJZSpUhtLJ-eE6KTJpalgxyypRyR3yIQrGMAP4z8HvJLGmgFeALoITOIO8MBUwoqHBG-lhH3hnTnxrhRT7Ufg6oREAZHDsPxAuhvMq-acs1Q6A
     * expiredTime : 1584066018
     */

    private String tmpSecretId;
    private String tmpSecretKey;
    private String sessionToken;
    private long expiredTime;

    private long beginTime = System.currentTimeMillis()/1000;

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public String getTmpSecretId() {
        return tmpSecretId;
    }

    public void setTmpSecretId(String tmpSecretId) {
        this.tmpSecretId = tmpSecretId;
    }

    public String getTmpSecretKey() {
        return tmpSecretKey;
    }

    public void setTmpSecretKey(String tmpSecretKey) {
        this.tmpSecretKey = tmpSecretKey;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
