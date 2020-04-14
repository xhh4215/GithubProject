package com.xiaohei.githubproject.utils

import com.bennyhuo.github.common.sharedpreferences.Preference
import com.xiaohei.githubproject.app.AppContext
import kotlin.reflect.jvm.jvmName

/**
 * Created by benny on 6/23/17.
 */
inline fun <reified R, T> R.pref(default: T) = Preference(AppContext, "", default, R::class.jvmName)