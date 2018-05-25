package me.leig.photowallandroid.image

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_image.view.*
import me.leig.photowallandroid.comm.Constant
import me.leig.photowallandroid.R
import me.leig.photowallandroid.comm.BaseAdapter
import me.leig.photowallandroid.comm.ViewHolder
import me.leig.photowallandroid.image.bean.ImageBean

/**
 *
 *
 * @author i
 * @version 20171231
 * @date 2018/3/18
 *
 */

class ImagesAdapter(private val context: Context, layoutId: Int, datas: List<ImageBean>): BaseAdapter<ImageBean>(context, layoutId, datas) {

    override fun convert(holder: ViewHolder, t: ImageBean) {
        Glide.with(context).load(Constant.SERVICE_ADDRESS + t.saveUrl).into(holder.itemView.iv_image)
        holder.setOnClickListener(R.id.iv_image, View.OnClickListener {
            when(it.id) {
                R.id.iv_image -> {
                    println("点击了第${holder.layoutPosition}张图片,地址是:${t.saveUrl}")
                    val ca = context as Activity
                    val ft = ca.fragmentManager.beginTransaction()
                    val ifv = ImageFragment()
                    val bundle = Bundle()
                    bundle.putString("URL", t.saveUrl)
                    ifv.arguments = bundle
                    ft.replace(R.id.fl_main, ifv)
                    ft.addToBackStack(null)
                    ft.commit()
                }
            }
        })
    }

}