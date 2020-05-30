package com.xiaohei.mvp.view

import com.xiaohei.mvp.presenter.IPresenter
import com.xiaohei.mvp.listener.ILifecycle

interface IMvpView<out Presenter:IPresenter<IMvpView<Presenter>>>:ILifecycle {
    val presenter:Presenter
}