package module.common.data.entity;




/**
 * @describe: 历史搜索
 * @date: 2019/10/8
 * @author: Mr Bai
 */
public class HistorySearch  {

    /**
     * @describe: userId+keyWord的hashcode
     * @date: 2019/10/8
     */

    private String historyId;

   
    private String keyWord;

   
    private String userId;

    private long updateTime;

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
