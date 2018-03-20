package me.leig.photowallandroid

import android.app.Application
import android.util.Log

/**
 *
 *
 * @authori
 * @version 20171231
 * @date 2018/3/18
 *
 */

class App: Application() {

    private val tag = App::class.java.name

    override fun onCreate() {
        super.onCreate()
        Log.i(tag, "starting")
    }

}