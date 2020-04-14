package com.xiaohei.mvp.Presenter

import com.xiaohei.mvp.View.IView
import com.xiaohei.mvp.listener.ILifecycle

interface IPresenter< out View:IView<IPresenter<View>>>:ILifecycle {
    val view:View
}