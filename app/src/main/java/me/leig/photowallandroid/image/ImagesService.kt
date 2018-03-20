package me.leig.photowallandroid.image

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url



/**
 *
 *
 * @author i
 * @version 20171231
 * @date 2018/3/18
 *
 */

interface ImagesService {

    @POST("api/v1/file/")
    fun getImages(@Body body: RequestBody): Call<ResponseBody>

    @GET
    fun downloadFile(@Url fileUrl: String): Call<ResponseBody>
}