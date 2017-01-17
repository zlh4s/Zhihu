package com.yiyi.zhihu.loader;

import android.content.Context;
import android.widget.ImageView;

/**
 *
 * Created by yiyi on 2017/1/10.
 */

public abstract class BaseImageLoader implements ImageLoaderInterface<ImageView> {
    @Override
    public ImageView creteImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}
