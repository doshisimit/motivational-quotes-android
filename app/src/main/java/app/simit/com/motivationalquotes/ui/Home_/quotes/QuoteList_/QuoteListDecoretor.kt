package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.simit.com.motivationalquotes.Utils_.Conveters

class QuoteListDecoretor(val mContext: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val space: Int = Conveters.convertDpToPixel(mContext, 8)


        val itemPosition = parent.getChildLayoutPosition(view)

        if (itemPosition == 0 || itemPosition == 1) {
            outRect.top = space
        }
        outRect.bottom = space

        if (itemPosition % 2 == 0) {
            outRect.left = space
            outRect.right = space / 2
        } else {
            outRect.right = space
            outRect.left = space / 2


        }

    }


    fun calculateSingleWidth(
            PxMargin: Int,
            CountItemScreen: Int
    ): Int {
        val totalScreenWidth = mContext.resources.displayMetrics.widthPixels
        val SapaceAllocated = totalScreenWidth - (CountItemScreen + 1) * PxMargin
        return SapaceAllocated / CountItemScreen
    }
}