package com.xiaohei.githubproject.view

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.doOnLayout
import com.xiaohei.common.ext.no
import com.xiaohei.common.ext.otherwise
import com.xiaohei.githubproject.R
import com.xiaohei.githubproject.model.AccountManager
import com.xiaohei.githubproject.network.entities.User
import com.xiaohei.githubproject.utils.doOnLayoutAvailable
import com.xiaohei.githubproject.utils.loadWithGlide
import com.xiaohei.githubproject.utils.showFragment
import com.xiaohei.githubproject.view.fragments.AboutFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.toast

/***
 *
 */
class MainActivity : AppCompatActivity(),AccountManager.OnAccountStateChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toogle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_close,
            R.string.navigation_drawer_open
        )
        drawer_layout.setDrawerListener(toogle)
        toogle.syncState()
        initNavigationView()
        AccountManager.onAccountStateChangeListeners.add(this)
        showFragment(R.id.fragmentContainer,AboutFragment::class.java)
        title="about"
    }

    override fun onDestroy() {
        super.onDestroy()
        AccountManager.onAccountStateChangeListeners.remove(this)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }
    /***
     *   run Calls the specified function [block] with `this` value as its receiver and returns its result.
     *       Calls the specified function [block] with `this` value as its argument and returns its result.

     */
    private fun initNavigationView(){
        AccountManager.currentUser?.let { ::updateNavigationView }?:clearNavigationView()
        initNavigationHeaderEvent()
    }
    private fun initNavigationHeaderEvent(){
        navigationView.doOnLayoutAvailable {
            AccountManager.isLogedIn().no {

            }.otherwise {
                AccountManager.logout()
                    .subscribe({
                      toast("注销成功")
                    },{
                        it.printStackTrace()
                    })
            }
        }
    }

    private fun updateNavigationView(user:User){
        navigationView.doOnLayoutAvailable {
            usernameView.text = user.login
            emailView.text = user.email?:""
            avatarView.loadWithGlide(user.avatar_url,user.login.first())
        }
    }

    private fun clearNavigationView(){
        navigationView.doOnLayoutAvailable {
            usernameView.text = "请登录"
            emailView.text = ""
            avatarView.imageResource = R.drawable.ic_github
        }
    }

    override fun onLogin(user: User) {
        updateNavigationView(user)
    }

    override fun onLogout() {
        clearNavigationView()
    }

}
