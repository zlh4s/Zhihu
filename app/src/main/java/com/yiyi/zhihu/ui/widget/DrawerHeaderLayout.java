package com.yiyi.zhihu.ui.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyi.zhihu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiyi on 2016/12/29.
 */

public class DrawerHeaderLayout extends LinearLayoutCompat {
    @BindView(R.id.action_download)
    Button downLoadBtn;
    @BindView(R.id.action_favorites)
    Button favoritesBtn;
    @BindView(R.id.user_icon)
    ImageView userIconIV;
    @BindView(R.id.login)
    TextView loginTV;


    public DrawerHeaderLayout(Context context) {
        this(context, null);
    }

    public DrawerHeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DrawerHeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        ButterKnife.bind(this);
    }
}
