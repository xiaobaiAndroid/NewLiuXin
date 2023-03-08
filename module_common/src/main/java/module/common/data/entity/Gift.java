package module.common.data.entity;

import java.io.Serializable;

/**
 * @describe: 礼物
 * @date: 2020/3/8
 * @author: Mr Bai
 */
public class Gift implements Serializable {

    private String id;

    private String giftName;
    private String giftUrl;

    private String giftTypeId;

    private String giftPrice;

    /*礼物类型 1-svga 2-gif 3-png*/
    private String giftType;

    /*giftSvgaUrl动画url*/
    private String giftSvgaUrl;

    /*排序*/
    private String displayOrder;

    /*是否选中*/
    private boolean isSelected;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftUrl() {
        return giftUrl;
    }

    public void setGiftUrl(String giftUrl) {
        this.giftUrl = giftUrl;
    }

    public String getGiftTypeId() {
        return giftTypeId;
    }

    public void setGiftTypeId(String giftTypeId) {
        this.giftTypeId = giftTypeId;
    }

    public String getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(String giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getGiftSvgaUrl() {
        return giftSvgaUrl;
    }

    public void setGiftSvgaUrl(String giftSvgaUrl) {
        this.giftSvgaUrl = giftSvgaUrl;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /*礼物类型 1-svga 2-gif 3-png*/
    public static class Type {
        public static final String SVGA = "1";
        public static final String GIF = "2";
        public static final String PNG = "3";
    }
}
