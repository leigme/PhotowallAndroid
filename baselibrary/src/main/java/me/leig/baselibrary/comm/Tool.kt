package me.leig.baselibrary.comm

import java.text.SimpleDateFormat
import java.util.*

/**
 *
 *
 * @author leig
 * @version 20170301
 *
 */
 
object Tool {

    /**
     * 字符串转时间
     *
     */
    @JvmStatic
    fun stringToDate(strDate: String = ""): Date {
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

}

