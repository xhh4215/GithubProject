package com.xiaohei.githubproject.network.services

import com.bennyhuo.github.settings.Configs
import com.xiaohei.githubproject.network.entities.AuthorizationReq
import com.xiaohei.githubproject.network.entities.AuthorizationRsp
import com.xiaohei.githubproject.network.retrofit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path
import rx.Observable

interface AuthApi{

    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerPrint}")
    fun createAuthorization(@Body req: AuthorizationReq, @Path("fingerPrint") fingerPrint: String = Configs.Account.fingerPrint)
        : Observable<AuthorizationRsp>

    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path("id") id: Int): Observable<Response<Any>>

}

/***
 * 通过lazy创建了一个retrofit的对象
 * 调用retrofit的create()方法创建对应的servcies
 */
object AuthService: AuthApi by retrofit.create(AuthApi::class.java)