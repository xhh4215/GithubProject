package com.xiaohei.githubproject.network.services

import com.xiaohei.githubproject.network.entities.User
import com.xiaohei.githubproject.network.retrofit
import retrofit2.http.GET
import rx.Observable

interface UserApi {

    @GET("/user")
    fun getAuthenticatedUser(): Observable<User>
}

object UserService: UserApi by retrofit.create(UserApi::class.java)