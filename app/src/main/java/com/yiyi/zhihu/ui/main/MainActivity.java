package com.yiyi.zhihu.ui.main;

import android.content.Context;
import android.content.res.Resources;
import android.os.health.PidHealthStats;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.yiyi.zhihu.R;
import com.yiyi.zhihu.api.Networks;
import com.yiyi.zhihu.common.BaseActivity;
import com.yiyi.zhihu.entity.themeDaily.ThemesEntity;
import com.yiyi.zhihu.ui.drawer.DisplaybleItem;
import com.yiyi.zhihu.ui.drawer.DrawerHeaderItem;
import com.yiyi.zhihu.ui.drawer.DrawerHomeItem;
import com.yiyi.zhihu.ui.drawer.HYDrawerMenuAdapter;
import com.yiyi.zhihu.ui.login.LoginAcitivity;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements HYDrawerMenuAdapter.onItemClickListener, Toolbar.OnMenuItemClickListener{

    @BindView(R.id.drawer_menu_rc)
    RecyclerView mDrawerMenuRC;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolBar)
    Toolbar mtoolBar;


    private static final String TAG = "MainActivity";

    private List<DisplaybleItem> mMainMenuList;

    private HYDrawerMenuAdapter mMenuAdapter;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
    }

    @Override
    public void initData() {
        super.initData();
        mMainMenuList = new ArrayList<>();
        mMainMenuList.add(new DrawerHeaderItem());
        mMainMenuList.add(new DrawerHomeItem());
        getData();
    }

    private void getData() {
        Networks.getInstance().getThemeApi().getThemes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThemesEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ThemesEntity themesEntity) {
                        mMainMenuList.addAll(themesEntity.getOthers());
                    }
                });
    }

    @Override
    public void initView() {
        initToolBar();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mDrawerMenuRC.setLayoutManager(layoutManager);
        mMenuAdapter = new HYDrawerMenuAdapter(mContext, mMainMenuList);
        mDrawerMenuRC.setAdapter(mMenuAdapter);
    }

    private void initToolBar() {
        mtoolBar.inflateMenu(R.menu.menu_main);
        Resources.Theme theme = getTheme();
        TypedValue navIcon = new TypedValue();
        TypedValue overFlowIcon = new TypedValue();

        theme.resolveAttribute(R.attr.navIcon, navIcon, true);
        theme.resolveAttribute(R.attr.overFlowIcon, overFlowIcon, true);

        mtoolBar.setNavigationIcon(navIcon.resourceId);
        mtoolBar.setOverflowIcon(ContextCompat.getDrawable(this, overFlowIcon.resourceId));
    }

    @Override
    public void initListener() {
        mtoolBar.setOnMenuItemClickListener(this);
        mtoolBar.setNavigationOnClickListener(this);
        mMenuAdapter.setOnItemClickListener(this);


    }

    @OnClick()
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            default:
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawers();
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
        }
    }


    @Override
    public void onDrawerHeaderItemClick() {
        openActivity(LoginAcitivity.class);
    }

    @Override
    public void onDrawerHomeClick(int position) {
        // TODO: 2016/12/30 switchHome
        mMenuAdapter.setSelection(position);
        mMenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemViewClick(View view, RecyclerView.ViewHolder holder, int position, int offset) {
        // TODO: 2016/12/30 switchTheme
        mMenuAdapter.setSelection(position);
        mMenuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFollowIVClick(View view, RecyclerView.ViewHolder holder, int position, int offset) {
        showShortToast("关注成功 内容将会在主页显示");
    }

    /**
     * ToolBar menu item click callback
     * @param item menu item
     * @return
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notice:
                // TODO: 2016/12/28 是否登录判断
                if (false) {
                    // TODO: 2016/12/28 跳转账号消息界面
                } else {
                    openActivity(LoginAcitivity.class);
                }
                break;

            case R.id.action_switch_readModel:
                // TODO: 2016/12/28 日夜模式切换

                break;

            case R.id.action_config:
                // TODO: 2016/12/28 跳转配置界面

                break;

        }
        return true;
    }
}
