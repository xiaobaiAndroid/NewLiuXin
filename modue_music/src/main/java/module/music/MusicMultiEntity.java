package module.music;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import module.common.data.entity.Music;
import module.common.data.entity.MusicCategory;

public class MusicMultiEntity implements MultiItemEntity {

    private int type;

    private MusicCategory category;

    private Music music;

    public MusicMultiEntity(int type) {
        this.type = type;
    }

    public MusicCategory getCategory() {
        return category;
    }

    public void setCategory(MusicCategory category) {
        this.category = category;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    public int getItemType() {
        return type;
    }

    enum Type{
        CATEGORY,/*分类*/
        CONTENT,/*内容*/
    }
}
