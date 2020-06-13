package app.simit.com.motivationalquotes.Utils_

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcelable


class Prefrence(mContext: Context) {
    companion object {
        private val APP_PREF: String = "Motivational_app_pref"
    }

    private lateinit var sharedpreferences: SharedPreferences

    init {
        sharedpreferences = mContext.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
    }

    public fun putInt(key: String, intVal: Int) {
        val editor = sharedpreferences.edit()
        editor.putInt(key, intVal)
        editor.apply()
    }



    public fun getInt(key: String): Int {
        return sharedpreferences.getInt(key, 0)
    }

}