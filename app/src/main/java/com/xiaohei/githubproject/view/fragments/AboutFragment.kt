package com.xiaohei.githubproject.view.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xiaohei.githubproject.R
import com.xiaohei.githubproject.utils.markdownText
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.nestedScrollView


/**
 * Created by benny on 7/9/17.
 */
class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return AboutFragmentUI().createView(AnkoContext.create(context!!, this))
    }
}

class AboutFragmentUI: AnkoComponent<AboutFragment> {
    override fun createView(ui: AnkoContext<AboutFragment>) = ui.apply {
        nestedScrollView {
            verticalLayout {
                imageView {
                    imageResource= R.mipmap.ic_launcher
                }.lparams(width= wrapContent,height = wrapContent){
                    gravity=Gravity.CENTER_VERTICAL
                }
                textView("GitHub"){
                    textColor = R.color.colorPrimary
                }.lparams(width= wrapContent,height = wrapContent){
                    gravity=Gravity.CENTER_VERTICAL
                }
                textView("By XH4215"){
                    textColor = R.color.colorPrimary
                }.lparams(width= wrapContent,height = wrapContent){
                    gravity=Gravity.CENTER_VERTICAL
                }
                textView(R.string.open_source_licenses){
                    textColor = R.color.colorPrimary
                    onClick {
                        alert {
                            customView{
                                scrollView{
                                    textView{
                                        padding = dip(10)
                                        markdownText= context.assets.open("license.md").bufferedReader().readText()
                                    }
                                }
                            }
                        }.show()
                    }
                }.lparams(width= wrapContent,height = wrapContent){
                    gravity=Gravity.CENTER_VERTICAL
                }
            }.lparams(width= wrapContent,height = wrapContent){
                gravity = Gravity.CENTER
            }
        }
    }.view

}