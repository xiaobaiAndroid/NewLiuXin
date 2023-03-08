package lib.share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

public class BitmapHelper {



    /**
     * 根据文件路径获取指定大小的Bitmap对象
     * @param path        文件路径
     * @param height    高度
     * @param width        宽度
     * @return
     */
    public static Bitmap getBitmapFromFile(String path, int height, int width){
        if (TextUtils.isEmpty(path)) {
            throw new IllegalArgumentException("参数为空，请检查你选择的路径:" + path);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只读取图片，不加载到内存中
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateSampleSize(height, width, options);
        options.inJustDecodeBounds = false;//加载到内存中
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /**
     * 计算所需图片的缩放比例
     * @param height    高度
     * @param width        宽度
     * @param options    options选项
     * @return
     */
    private static int calculateSampleSize(int height, int width, BitmapFactory.Options options){
        int realHeight = options.outHeight;
        int realWidth = options.outWidth;
        int heigthScale = realHeight / height;
        int widthScale = realWidth / width;
        if(widthScale > heigthScale){
            return widthScale;
        }else{
            return heigthScale;
        }
    }


}
