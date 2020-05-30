package com.xiaohei.githubproject.presenter

import com.xiaohei.githubproject.model.AccountManager
import com.xiaohei.githubproject.view.LoginActivity
import com.xiaohei.mvp.presenter.impl.BasePresenter
import rx.android.BuildConfig

class LoginPresenter :BasePresenter<LoginActivity>(){
  fun doLogin(username:String,password:String){
      AccountManager.usename =username
      AccountManager.password = password
      view.onLoginStart()
      AccountManager.login()
          .subscribe({
              view.onLoginSuccess()
          },{
             view.onLoginError(it)
          }
          )

  }

  fun checkUserName(username:String):Boolean{
      return true
  }
  fun checkPassword(password: String):Boolean{
      return true
  }

    override fun onResume() {
        super.onResume()
        if(BuildConfig.DEBUG){
            view.onDataInit(AccountManager.usename,AccountManager.password)
//            view.onDataInit(BuildConfig.testUsername,BuildConfig.testPassword)
        }else{
            view.onDataInit(AccountManager.usename,AccountManager.password)

        }
    }
}