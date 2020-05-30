package com.xiaohei.mvp.presenter.impl

import android.content.res.Configuration
import android.os.Bundle
import com.xiaohei.mvp.presenter.IPresenter
import com.xiaohei.mvp.view.IMvpView

abstract class BasePresenter <out V:IMvpView<BasePresenter<V>>>:IPresenter<V>{

    override lateinit var view:  @UnsafeVariance V
     override fun onCreate(savedInstanceState: Bundle?) {
     }
    override fun onConfigurationChanged(newConfig: Configuration) {
     }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
     }
    override fun onDestroy() {
     }

    override fun onPause() {
     }

    override fun onResume() {
     }

    override fun onSaveInstanceState(outState: Bundle) {
     }

    override fun onStart() {
     }

    override fun onStop() {
     }
}