package com.xiaohei.githubproject.network

import com.xiaohei.common.ext.ensureDir
import com.xiaohei.githubproject.app.AppContext
import com.xiaohei.githubproject.network.interceptors.AcceptIntercepter
import com.xiaohei.githubproject.network.interceptors.AuthInterceptor
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory2
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
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
        .addCallAdapterFactory(RxJavaCallAdapterFactory2.createWithSchedulers(Schedulers.io(), AndroidSchedulers.mainThread()))
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .cache(Cache(cacheFile,1024*1024*1024))
                .addInterceptor(AcceptIntercepter())
                .addInterceptor(AuthInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .build()
        )
        .baseUrl(BASE_URL)
        .build()
}