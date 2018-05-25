package me.leig.photowallandroid.comm

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 *
 *
 * @authori
 * @version 20171231
 * @date 2018/3/18
 *
 */

abstract class BaseAdapter<in T> constructor(private val context: Context, val layout: Int, private val dataList: List<T>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(context, layout, parent)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        convert(holder, dataList[position])
    }

    abstract fun convert(holder: ViewHolder, t: T)
}