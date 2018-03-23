package me.leig.baselibrary.fragment

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import me.leig.baselibrary.comm.BaseInterface

/**
 * 视图基类
 *
 * @author leig
 * @version 20180301
 *
 */

abstract class BaseFragment: Fragment(), View.OnTouchListener, View.OnClickListener, BaseInterface {

    private val toast = Toast.makeText(activity, "", Toast.LENGTH_SHORT)

    lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        mView = inflater.inflate(getLayoutId(), container, false)
        mView.setBackgroundColor(Color.WHITE)
        mView.setOnTouchListener(this)
        initData()
        initView()
        initListener()
        return mView
    }

    abstract fun getLayoutId(): Int

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return true
    }

    /**
     * 简单日志打印
     *
     */
    fun log(msg: String) {
        Log.d(getClassTag(), msg)
    }

    /**
     * 简单吐司提示
     *
     */
    fun toast(msg: String) {
        activity.runOnUiThread {
            toast.setText(msg)
            toast.show()
        }
    }
}