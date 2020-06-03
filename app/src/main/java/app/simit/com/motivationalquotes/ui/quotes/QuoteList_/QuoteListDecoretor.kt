package app.simit.com.motivationalquotes.ui.quotes.QuoteList_

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class QuoteListDecoretor(val mContext: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val space: Int = convertDpToPixel(8)


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

    fun convertDpToPixel(dp: Int): Int {
        return (dp * (mContext.resources
                .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
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