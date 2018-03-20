package me.leig.photowallandroid.comm

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.app.Activity
import android.app.Fragment
import android.util.Log

/**
 *
 *
 * @author leig
 * @version 20171231
 * @date 2018/3/17
 *
 */

abstract class BaseFragment constructor(val layout: Int, open val title: String): Fragment(), View.OnTouchListener, BaseInterface, View.OnClickListener {

    lateinit var baseView: View

    override fun initBaseData() {
        print("initBaseData")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        baseView = inflater.inflate(layout, container, false)
        baseView.setBackgroundColor(Color.WHITE)

        initData()

        initView()

        initListener()

        return baseView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setOnTouchListener(this)
    }

    override fun onTouch(v: View, event: MotionEvent?): Boolean {
        return true
    }

    override fun initView() {
        print("initView")
    }

    override fun initData() {
        print("initData")
    }

    override fun initListener() {
        print("initListener")
    }

    override fun onClick(v: View) {
        print("onClick")
    }

    protected fun startActivity(clazz: Class<out Activity>) {
        val intent = Intent(activity, clazz)
        startActivity(intent)
    }

    fun print(content: String) {
        Log.i(title, content)
    }
}