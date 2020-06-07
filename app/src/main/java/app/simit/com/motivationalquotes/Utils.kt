package app.simit.com.motivationalquotes

import android.content.Context
import android.util.DisplayMetrics

class Utils {
    companion object {
        fun convertDpToPixel(mContext: Context, dp: Int): Int {
            return (dp * (mContext.resources
                    .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
        }
    }
}