package me.leig.photowallandroid.image.bean

import me.leig.photowallandroid.comm.Constant

/**
 *
 *
 * @author leig
 * @version 20180301
 *
 */

data class RequestImage(
        var page: Int = 1,
        var limit: Int = Constant.DEFAULT_PAGE_NUM,
        var file: ImageBean? = null
)