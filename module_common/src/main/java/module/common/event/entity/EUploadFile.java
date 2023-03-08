package module.common.event.entity;

/**
 * @describe: 上传文件的实体
 *
 * @date: 2020/3/13
 * @author: Mr Bai
 */
public class EUploadFile {

    /*上传进度*/
    private int progress;


    private int result;

    private String message;

    /*服务器地址*/
    private String remoteUrl;

    /*本地上传的路径*/
    private String uploadUrl;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }
}
