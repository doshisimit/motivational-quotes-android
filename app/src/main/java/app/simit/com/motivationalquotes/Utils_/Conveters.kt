package app.simit.com.motivationalquotes.Utils_

import android.content.Context
import android.util.DisplayMetrics

class Conveters {
    companion object {
        fun convertDpToPixel(mContext: Context, dp: Int): Int {
            return (dp * (mContext.resources
                    .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
        }
    }
}