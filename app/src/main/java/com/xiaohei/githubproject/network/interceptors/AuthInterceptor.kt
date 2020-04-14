package com.xiaohei.githubproject.network.interceptors

import android.util.Base64
import com.xiaohei.githubproject.model.AccountManager
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class AuthInterceptor:Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        //保存原来的请求
        val original = chain.request()
        return chain.proceed(original.newBuilder()
            .apply {
                when{
                    original.url().pathSegments().contains("authorizations")->{
                       val userCredentials = AccountManager.usename+":"+AccountManager.password
                       val auth = "Basic"+String(Base64.encode(userCredentials.toByteArray(),Base64.DEFAULT)).trim()
                        header("authorizations",auth)
                    }
                    AccountManager.isLogedIn()->{
                        val auth = "Token"+AccountManager.token
                        header("authorizations",auth)
                    }
                    else -> removeHeader("Authorization")

                }
            }
            .build())
    }

}