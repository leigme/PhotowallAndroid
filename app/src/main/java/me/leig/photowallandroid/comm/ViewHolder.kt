package me.leig.photowallandroid.comm

import android.content.Context
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.SparseArray
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View

/**
 *
 *
 * @authori
 * @version 20171231
 * @date 2018/3/18
 *
 */

class ViewHolder constructor(context: Context, layoutId: Int, parent: ViewGroup, private val mConvertView: View? = LayoutInflater.from(context).inflate(layoutId, parent, false)) : ViewHolder(mConvertView) {

    private val mViews: SparseArray<View> = SparseArray()

    fun setOnClickListener(viewId: Int, listener: View.OnClickListener): me.leig.photowallandroid.comm.ViewHolder {
        val view = getView(viewId)
        view.setOnClickListener(listener)
        return this
    }

    private fun getView(viewId: Int): View {
        var view = mViews.get(viewId)
        if (null == view) {
            view = mConvertView!!.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view
    }

}