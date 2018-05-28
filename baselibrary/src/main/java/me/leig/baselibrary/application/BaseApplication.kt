package me.leig.baselibrary.application

import android.app.Application

/**
 *
 *
 * @author leig
 * @version 20180301
 *
 */

abstract class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initData()
        initServer()
    }

    abstract fun initData()

    abstract fun initServer()

}