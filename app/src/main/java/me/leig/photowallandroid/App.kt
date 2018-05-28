package me.leig.photowallandroid

import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import me.leig.baselibrary.application.BaseApplication

/**
 * 启动入口
 *
 * @author leig
 * @version 20171231
 * @date 2018/3/18
 *
 */

class App: BaseApplication() {

    private val tag = App::class.java.name

    override fun initData() {
        Log.i(tag, "初始化数据")
    }

    override fun initServer() {
        Log.i(tag, "初始化服务")
    }

}