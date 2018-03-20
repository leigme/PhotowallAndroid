package me.leig.photowallandroid.comm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 *
 *
 * @author i
 * @version 20171231
 * @date 2018/3/18
 *
 */

abstract class BaseActivity(open val title: String): AppCompatActivity(), BaseInterface {

    override fun initBaseData() {
        print("initBaseData")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getRootView())

        initBaseData()

        initView()

        initData()

        initListener()
    }

    abstract fun getRootView(): Int

    override fun initData() {
        print("initData")
    }

    override fun initView() {
        print("initView")
    }

    override fun initListener() {
        print("initListener")
    }

    protected fun startActivity(clazz: Class<out Activity>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    fun print(content: String) {
        Log.i(title, content)
    }

}