package com.xiaohei.githubproject.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.bennyhuo.common.ext.hideSoftInput
import com.xiaohei.common.ext.otherwise
import com.xiaohei.common.ext.yes
import com.xiaohei.githubproject.R
import com.xiaohei.githubproject.presenter.LoginPresenter
import com.xiaohei.mvp.view.impl.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signInButton.onClick {
            presenter
                .checkUserName(username.text.toString())
                .yes {
                    presenter.checkPassword(password.text.toString())
                        .yes {
                            hideSoftInput()
                            presenter.doLogin(username.text.toString(),password.text.toString())
                        }.otherwise {
                            showTips(password,"密码不合法")
                        }
                }.otherwise {
                    showTips(username,"用户名不合法")
                }
        }
    }
    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
        loginForm.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                loginForm.visibility = if (show) View.GONE else View.VISIBLE
            }
        })

        loginProgress.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                loginProgress.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }
    private fun showTips(view:EditText,message:String){
        view.requestFocus()
        view.error = message
    }

    fun onLoginStart(){
        showProgress(true)
    }

    fun onLoginError(t:Throwable){
        showProgress(false)

        toast("登陆失败")
    }
    fun  onLoginSuccess(){
        showProgress(false)

        toast("登陆成功")
    }

    fun onDataInit(name:String,pwd:String){
        username.setText(name)
        password.setText(pwd)

    }
}
