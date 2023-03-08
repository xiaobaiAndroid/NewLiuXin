package module.common.data.request;

import module.common.type.LanguageType;

import java.util.HashMap;
import java.util.Map;


/**
 * @describe: 公用请求查询对象
 * @date: 2020/7/9
 * @author: Mr Bai
 */
public class QueryObjReq {

    private int pageNumber=1;

    private int pageSize=10;
    private int languageMarket= LanguageType.CN.getValue();
    private int cateLanguage= LanguageType.CN.getValue();

    private Map<String,Object> queryObj = new HashMap<>();

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(Map<String, Object> queryObj) {
        this.queryObj = queryObj;
    }

    public int getLanguageMarket() {
        return languageMarket;
    }

    public void setLanguageMarket(int languageMarket) {
        this.languageMarket = languageMarket;
    }

    public int getCateLanguage() {
        return cateLanguage;
    }

    public void setCateLanguage(int cateLanguage) {
        this.cateLanguage = cateLanguage;
    }
}
