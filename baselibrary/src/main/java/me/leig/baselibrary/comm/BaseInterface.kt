package me.leig.baselibrary.comm

/**
 * 基本接口;视图控制器基类实现
 *
 * @author leig
 * @version 20180301
 *
 */

interface BaseInterface {

    fun initBaseData()

    /**
     * 初始化数据
     *
     */
    fun initData()

    /**
     * 初始化视图
     *
     */
    fun initView()

    /**
     * 初始化事件监听
     *
     */
    fun initEvent()
}