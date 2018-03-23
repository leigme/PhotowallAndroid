package me.leig.photowallandroid.image

import me.leig.photowallandroid.comm.Constant
import java.util.*

/**
 *
 *
 * @author i
 * @version 20171231
 * @date 2018/3/18
 *
 */

data class ImageBean(
        var id: Int? = null,
        var title: String? = null,
        var content: String? = null,
        var parentDir: String? = null,
        var tempUrl: String? = null,
        var saveUrl: String? = null,
        var fileSize: Long? = null,
        var fileType: Int? = null,
        var mimeType: String? = null,
        var longitude: String? = null,
        var latitude: String? = null,
        var deleteFlag: Int = Constant.DELETEFLAG_NORMAL,
        var startTime: Date? = null,
        var endTime: Date? = null,
        var limitNum: Int = Constant.DEFAULT_PAGENUM,
        var pageNum: Int = 1
)