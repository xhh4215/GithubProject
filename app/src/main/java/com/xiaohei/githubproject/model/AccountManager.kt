package com.xiaohei.githubproject.model

import com.google.gson.Gson
import com.xiaohei.githubproject.network.entities.AuthorizationReq
import com.xiaohei.githubproject.network.entities.AuthorizationRsp
import com.xiaohei.githubproject.network.entities.User
import com.xiaohei.githubproject.network.services.AuthService
import com.xiaohei.githubproject.network.services.UserService
import com.xiaohei.githubproject.utils.formJson
import com.xiaohei.githubproject.utils.pref
import retrofit2.HttpException
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

object AccountManager {
    var usename by pref("")
    var authid by pref(-1)
    var password by pref("")
    var token by pref("")
    private var userJson by pref("")

    var currentUser : User? = null
      get() {
          if(field==null&& userJson.isNotEmpty()){
              field = Gson().formJson<User>(userJson)
          }
          return field
      }
      set(value){
          if (value==null){
              userJson = ""
          }else{
              userJson = Gson().toJson(value)
          }
          field = value
      }
    fun isLogedIn():Boolean= token.isNotEmpty()


    fun login() = AuthService.createAuthorization(AuthorizationReq())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnNext{
           if (it.token.isEmpty()) throw AccountException(it)
        }
        .retryWhen{
            it.flatMap {
                if(it is AccountException){
                   AuthService.deleteAuthorization(it.authorizationRsp.id)
                }else{
                    Observable.error(it)
                }
            }
        }
        .flatMap {
            token = it.token
            authid = it.id
            UserService.getAuthenticatedUser()
        }
        .map {
            currentUser = it
        }

    fun logout()=AuthService.deleteAuthorization(authid)
        .doOnNext{
            if(it.isSuccessful){
                authid=-1
                token = ""
                currentUser = null
            }else{
                throw  HttpException(it)
            }
        }

    class AccountException(val authorizationRsp: AuthorizationRsp):Exception("already login"){

    }
}