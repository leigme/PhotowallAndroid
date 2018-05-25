package me.leig.photowallandroid.image

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 *
 *
 * @author leig
 * @version 20180301
 *
 */

abstract class EndLessOnScrollListener constructor(private val mLinearLayoutManager: LinearLayoutManager): RecyclerView.OnScrollListener() {

    private var totalItemCount = 0

    private var previousTotal = 0

    private var visibleItemCount = 0

    private var firstVisibleItem = 0

    private var currentPage = 0

    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        totalItemCount = mLinearLayoutManager.itemCount
        visibleItemCount = recyclerView!!.childCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && firstVisibleItem >= totalItemCount - visibleItemCount) {
            currentPage += 1
            onLoadMore(currentPage)
            loading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int)
}