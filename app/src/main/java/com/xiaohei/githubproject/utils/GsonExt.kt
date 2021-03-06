package com.xiaohei.githubproject.utils

import com.google.gson.Gson

/**
 * Created by benny on 1/20/18.
 */
inline fun <reified T> Gson.formJson(json: String) = fromJson(json, T::class.java)