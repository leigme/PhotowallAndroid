package me.leig.photowallandroid.comm

import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 *
 * @author leig
 * @version 20170301
 *
 */
 
class Tool

fun stringToDate(strDate: String): Date {
    return when(strDate.length) {
        10 -> {
            SimpleDateFormat("yyyy-MM-dd").parse(strDate)
        }
        19 -> {
            SimpleDateFormat("yyyy-MM-dd HH:mm:SS").parse(strDate)
        }
        else -> {
            Date()
        }
    }
}

inline fun <reified T> genericType() = object: TypeToken<T>() {}.type!!

fun downloadFile(body: ResponseBody, filePath: String): Boolean {
    try {
        val file = File(filePath)

        try {
            val bytes = ByteArray(1024)

            val fileSize: Long = body.contentLength()

            var fileSizeDownloaded: Long = 0

            val inputStream = body.byteStream()

            val outputStream = FileOutputStream(file)

            while (true) {

                val read = inputStream.read(bytes)

                if (-1 == read) {
                    break
                }

                outputStream.write(bytes, 0, read)

                fileSizeDownloaded += read
            }

            outputStream.flush()

            inputStream.close()
            outputStream.close()

            return true
        } catch (e: IOException) {
            return false
        } finally {
            println("下载结束...")
        }
    } catch (e: IOException) {
        return false
    }
}