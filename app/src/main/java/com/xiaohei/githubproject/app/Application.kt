package com.xiaohei.githubproject.app

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import com.bennyhuo.swipefinishable.SwipeFinishable
import com.bennyhuo.tieguanyin.runtime.core.ActivityBuilder

private lateinit var INSTANCE: Application

class Application: Application(){
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        ActivityBuilder.INSTANCE.init(this)
        SwipeFinishable.INSTANCE.init(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(base)
        super.attachBaseContext(base)
    }
}

object AppContext: ContextWrapper(INSTANCE)