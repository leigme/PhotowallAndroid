package me.leig.photowallandroid.image

import android.os.Environment
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_image.view.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import me.leig.photowallandroid.Constant
import me.leig.photowallandroid.R
import me.leig.photowallandroid.comm.BaseFragment
import me.leig.photowallandroid.comm.downloadFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import retrofit2.Retrofit

/**
 *
 *
 * @author leig
 * @version 20170301
 *
 */
 
class ImageFragment: BaseFragment(R.layout.fragment_image, "图片详情") {

    private var imageUrl = ""

    override fun initData() {
        imageUrl = arguments.getString("URL")
    }

    override fun initView() {
        if ("" != imageUrl) {

            val imageFile = Environment.getExternalStorageDirectory().absolutePath + "/images/" + imageUrl

            val file = File(imageFile)

            if (file.exists()) {
                Glide.with(activity).load(file).into(baseView.iv_image)
            } else {
                val fileDir = file.parentFile
                if (!fileDir.exists()) {
                    fileDir.mkdirs()
                }

                val rootFile = Environment.getExternalStorageDirectory().absoluteFile

                for (f in rootFile.list()) {
                    println("------>" + File(f).name)
                }

                File(Environment.getExternalStorageDirectory().absolutePath + "/测试一下/").mkdir()

                val client = OkHttpClient().newBuilder().addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                            .addHeader("creater_oid", "123411") //这里就是添加一个请求头
                            .build()
                    // 不依赖logging，用这三行也能打印出请求体
//                    val buffer = Buffer()
//                    request.body()!!.writeTo(buffer)
//                    Log.d(title, "intercept: " + buffer.readUtf8())

                    chain.proceed(request)
                }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

                val retrofit = Retrofit.Builder().baseUrl(Constant.SERVICEADDRESS).client(client).build()

                val service = retrofit.create(ImagesService::class.java)

                val call = service.downloadFile(imageUrl)

                call.enqueue(object: Callback<ResponseBody> {

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("下载失败..." + t.message)
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val result = downloadFile(response.body()!!, imageFile)
                            if (result) {
                                Glide.with(activity).load(file).into(baseView.iv_image)
                            }
                        }
                    }
                })
            }

        }
    }

    override fun onClick(v: View) {

    }

}