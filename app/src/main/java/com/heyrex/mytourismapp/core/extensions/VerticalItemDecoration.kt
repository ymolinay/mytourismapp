package com.heyrex.mytourismapp.core.extensions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecoration(private val verticalSpace: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = (verticalSpace / 2).toPx(view.context)
        outRect.top = (verticalSpace / 2).toPx(view.context)
    }
}