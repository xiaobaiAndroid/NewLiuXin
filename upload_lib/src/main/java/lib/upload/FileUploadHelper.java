package lib.upload;

import android.content.Context;

import com.tencent.cos.xml.CosXmlService;
import com.tencent.cos.xml.CosXmlServiceConfig;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.transfer.COSXMLUploadTask;
import com.tencent.cos.xml.transfer.TransferConfig;
import com.tencent.cos.xml.transfer.TransferManager;
import com.tencent.qcloud.core.auth.QCloudCredentialProvider;


/**
 * @describe: 文件上传帮助类
 * @date: 2020/3/12
 * @author: Mr Bai
 */
public class FileUploadHelper {


    /*
    * 10000	InvalidArgument	参数校验失败，例如必填参数为空。
10001	InvalidCredentials	密钥信息校验失败，例如密钥为空。
10002	BadRequest	SDK 配置错误，例如 APPID，region 配置出错。
10003	SinkSourceNotFound	输入源或者输出源错误，例如上传的文件不存在。
10004	UnsupportOperation	无法支持的操作。
20000	InternalError	内部错误，例如 xml 格式数据解析失败。
20001	ServerError	服务错误，例如返回了非 xml 格式数据。
20002	IOError	流读写 IO 异常，例如文件读写 IO 异常。
20003	NetworkError	网络出现异常，例如网络不可用，DNS 解析失败等。
20004	DataIntegrityError	数据完整性校验失败。
30000	UserCancelled	用户已取消了请求。
30001	AlreadyFinished	已执行过请求。
    *
    *
    * */

    private static CosXmlService init(Context context, CloudUploadSign uploadSign) {
        String region = "ap-guangzhou";

        CosXmlServiceConfig serviceConfig = new CosXmlServiceConfig.Builder()
                .setRegion(region)
                .isHttps(false) // 使用 HTTPS 请求，默认为 HTTP 请求
                .builder();

/**
 * 初始化 {@link QCloudCredentialProvider} 对象，来给 SDK 提供临时密钥
 */
        MyCredentialProvider myCredentialProvider = new MyCredentialProvider(uploadSign);

        return new CosXmlService(context, serviceConfig, myCredentialProvider);
    }


    /**
     * @describe: 上传
     * @date: 2020/3/13
     * @param uploadDir 腾讯云上存储桶的目录名
     */
    public static void upload2(Context context, CloudUploadSign uploadSign, String uploadDir,final String uploadPath,final UploadResultListener uploadResultListener) {

        CosXmlService cosXmlService = init(context, uploadSign);
        // 初始化 TransferConfig
        TransferConfig transferConfig = new TransferConfig.Builder().build();


// 初始化 TransferManager
        TransferManager transferManager = new TransferManager(cosXmlService, transferConfig);

        String bucket = "liuxin-1301077617"; //存储桶，格式：BucketName-APPID
        String cosPath = uploadDir+"/"+uploadPath.substring(uploadPath.lastIndexOf("/")+1); //对象在存储桶中的位置标识符，即称对象键


        // 上传对象
        COSXMLUploadTask cosxmlUploadTask = transferManager.upload(bucket, cosPath, uploadPath, null);

        //设置上传进度回调
        cosxmlUploadTask.setCosXmlProgressListener((complete, target) -> {
//            LogUtils.i("complete=" + complete);
            int progress = (int) ((complete * 1.0f / target)*100);
            if(uploadResultListener!=null){
                uploadResultListener.progress(progress);
            }
        });
        //设置返回结果回调
        cosxmlUploadTask.setCosXmlResultListener(new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                COSXMLUploadTask.COSXMLUploadTaskResult cOSXMLUploadTaskResult = (COSXMLUploadTask.COSXMLUploadTaskResult) result;

                if(uploadResultListener!=null){
                    uploadResultListener.success(result.accessUrl);
                }
            }

            @Override
            public void onFail(CosXmlRequest request, CosXmlClientException exception, CosXmlServiceException serviceException) {

                if(uploadResultListener!=null){
                    uploadResultListener.fail(exception.getMessage());
                }
            }
        });
    }

    public interface UploadResultListener{

        void success(String remoteUrl);

        void progress(int progress);

        void fail(String message);
    }

}
