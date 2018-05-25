package me.leig.photowallandroid.image

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_images.view.*
import me.leig.baselibrary.fragment.BaseFragment
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import org.json.JSONObject
import me.leig.photowallandroid.comm.Constant
import me.leig.photowallandroid.R
import me.leig.photowallandroid.comm.genericType
import me.leig.photowallandroid.comm.stringToDate
import me.leig.photowallandroid.image.bean.ImageBean
import me.leig.photowallandroid.image.bean.RequestImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*

/**
 *
 *
 * @author i
 * @version 20171231
 * @date 2018/3/17
 *
 */

class ImagesFragment: BaseFragment(ImageFragment::class.java.name) {

    private var year = 0
    private var month = 0
    private var day = 0

    private var startTime: String = ""
    private var endTime: String = ""

    private var starttimeFlag = false
    private var endtimeFlag = false

    private var pageNo = 1
    private var pageSize = 20

    override fun getLayoutId(): Int {
        return R.layout.fragment_images
    }

    override fun initView() {

        mView.tv_starttime.text = "开始时间: ${stringTime(year, month, day)}"
        mView.tv_endtime.text = "结束时间: ${stringTime(year, month, day)}"
    }

    override fun initData() {

        val client = OkHttpClient().newBuilder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("creater_oid", "123411") //这里就是添加一个请求头
                    .build()
            // 不依赖logging，用这三行也能打印出请求体
            val buffer = Buffer()
            request.body()!!.writeTo(buffer)
            Log.d(title, "intercept: " + buffer.readUtf8())

            chain.proceed(request)
        }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

        val cal = Calendar.getInstance()

        cal.time = Date()

        // 获取年份
        year = cal.get(Calendar.YEAR)
        // 获取月份
        month = cal.get(Calendar.MONTH) + 1
        // 获取日
        day = cal.get(Calendar.DATE)

        val retrofit = Retrofit.Builder().baseUrl(Constant.SERVICE_ADDRESS).client(client).build()

        val service = retrofit.create(ImagesService::class.java)

        val requestImage = RequestImage()

        val imageBean = ImageBean()

        imageBean.pageNum = pageNo
        imageBean.limitNum = pageSize
        imageBean.deleteFlag = Constant.DELETE_FLAG_NORMAL

        if ("" != startTime) {
            imageBean.startTime = stringToDate(startTime)
        }

        if ("" != endTime) {
            imageBean.endTime = stringToDate(endTime)
        }

        val requestBody = RequestBody.create(MediaType.parse("application/json"), Gson().toJson(requestImage))

        val call = service.getImages(requestBody)

        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                print("失败了..." + t!!.message)
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                val result = response.body()!!.string()
                val jsonObj = JSONObject(result)
                val fileInfos = jsonObj.get("fileInfos").toString()

                val type = genericType<List<ImageBean>>()

                val images: List<ImageBean> = Gson().fromJson(fileInfos, type)

                val iA = ImagesAdapter(activity, R.layout.item_image, images)
                mView.rv_images.layoutManager = GridLayoutManager(activity, 3)
                mView.rv_images.adapter = iA
                mView.rv_images.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                    }
                })
            }
        })
    }

    override fun initEvent() {
        mView.tv_starttime.setOnClickListener(this)
        mView.tv_endtime.setOnClickListener(this)
        mView.btn_search.setOnClickListener(this)
        mView.dp_date.init(year, month, day) { view, year, month, day ->
            view.visibility = View.GONE

            if (starttimeFlag && !endtimeFlag) {
                startTime = stringTime(year, month + 1, day)
                mView.tv_starttime.text = "开始时间: $startTime"
            } else if (!starttimeFlag && endtimeFlag) {
                endTime = stringTime(year, month + 1, day)
                mView.tv_endtime.text = "结束时间: $endTime"
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.tv_starttime -> {
                starttimeFlag = true
                endtimeFlag = false
                mView.dp_date.visibility = View.VISIBLE
                print("点击了开始时间")
            }
            R.id.tv_endtime -> {
                starttimeFlag = false
                endtimeFlag = true
                mView.dp_date.visibility = View.VISIBLE
                print("点击了结束时间")
            }
            R.id.btn_search -> {
                print("点击了搜索")
            }
        }
    }

    private fun stringTime(year: Int, month: Int, day: Int): String {

        val m: String = if (10 > month) {
            "0$month"
        } else {
            "$month"
        }

        val d: String = if (10 > day) {
            "0$day"
        } else {
            "$day"
        }

        return "$year-$m-$d"
    }


}