package com.xiaohei.mvp.presenter

import com.xiaohei.mvp.view.IMvpView
import com.xiaohei.mvp.listener.ILifecycle

interface IPresenter< out View:IMvpView<IPresenter<View>>>:ILifecycle {
    val view:View
}