package com.xiaohei.githubproject.network

import com.xiaohei.common.ext.ensureDir
import com.xiaohei.githubproject.app.AppContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL="https://api.github.com"
private val cacheFile by lazy {
    File(AppContext.cacheDir,"webServiceApi").apply {
        ensureDir()
    }
}
val retrofit by lazy {
    Retrofit.Builder()
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .cache(Cache(cacheFile,1024*1024*1024))
                .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .build()
        )
        .baseUrl(BASE_URL)
        .build()
}