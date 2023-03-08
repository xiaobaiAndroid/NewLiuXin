package module.common.data.respository;


import module.common.data.entity.UserInfo;

import java.lang.ref.SoftReference;

/**
 * @describe: 内存缓存类
 * @date: 2019/8/21
 * @author: Mr Bai
*/
public class MemoryCacheRepository {

    private SoftReference<UserInfo> userSoftReference;

    private MemoryCacheRepository(){

    }

    private static MemoryCacheRepository instance;

    public static MemoryCacheRepository getInstance(){
        if(instance==null){
            synchronized (MemoryCacheRepository.class){
                if(instance==null){
                    instance = new MemoryCacheRepository();
                }
            }
        }
        return instance;
    }


    public void saveUserInfo(UserInfo user){
        if(userSoftReference!=null){
            userSoftReference.clear();
        }
        userSoftReference = new SoftReference<>(user);
    }

    public UserInfo getUserInfo(){
        if(userSoftReference!=null){
            return userSoftReference.get();
        }
        return null;
    }

    public void clear(){
        if(userSoftReference!=null){
            userSoftReference.clear();
            userSoftReference=null;
        }
    }

}
