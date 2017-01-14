package com.yiyi.zhihu.ui.editor;

import com.yiyi.zhihu.entity.commonEntity.StoryContentEntity;
import com.yiyi.zhihu.mvpframe.base.BaseModel;
import com.yiyi.zhihu.mvpframe.base.BasePresenter;
import com.yiyi.zhihu.mvpframe.base.BaseView;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/11.
 */

public interface EditorContract {
    interface Model extends BaseModel {
        //Observable getEditorProfilePage(int id);

    }

    interface View extends BaseView {
        //void loadEditorProfilePage();

    }

    abstract class Presenter extends BasePresenter<Model, View> {
       // abstract void getEditorProfilePage(int id);

    }
}
