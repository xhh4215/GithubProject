package com.xiaohei.githubproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaohei.githubproject.R
import com.xiaohei.githubproject.presenter.LoginPresenter
import com.xiaohei.mvp.View.impl.BaseActivity

class LoginActivity : BaseActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
