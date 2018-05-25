package me.leig.photowallandroid.main.ui

import me.leig.baselibrary.activity.BaseActivity
import me.leig.photowallandroid.R
import me.leig.photowallandroid.image.ImagesFragment

class MainActivity : BaseActivity(MainActivity::class.java.name) {

    override fun getRootView(): Int {
        return R.layout.activity_main
    }

    override fun getFragmentId(): Int {
        return R.id.fl_main
    }

    override fun initView() {
        addFragment(ImagesFragment())
    }
}
