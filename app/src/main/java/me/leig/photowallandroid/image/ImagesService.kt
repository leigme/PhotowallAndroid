package me.leig.photowallandroid.image

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 *
 *
 * @author i
 * @version 20171231
 * @date 2018/3/18
 *
 */

interface ImagesService {

    @POST("v1/f/finds/")
    fun getImages(@Body body: RequestBody): Call<ResponseBody>

    @Streaming
    @GET
    fun downloadFile(@Url fileUrl: String): Call<ResponseBody>
}