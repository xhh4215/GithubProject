package com.xiaohei.mvp.View

import android.view.View
import com.xiaohei.mvp.Presenter.IPresenter
import com.xiaohei.mvp.listener.ILifecycle

interface IView<out Presenter:IPresenter<IView<Presenter>>>:ILifecycle {
    val presenter:Presenter
}