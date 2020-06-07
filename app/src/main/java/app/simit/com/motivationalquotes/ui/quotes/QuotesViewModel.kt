package app.simit.com.motivationalquotes.ui.quotes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.simit.com.motivationalquotes.Api.RetrofitInstance
import app.simit.com.motivationalquotes.ui.quotes.QuoteList_.Quote
import app.simit.com.motivationalquotes.ui.quotes.QuoteList_.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuotesViewModel : ViewModel() {
    companion object {
        private const val TAG: String = "QUOTES_VIEW_MODEL"
    }

    private val mQuotes: MutableLiveData<List<Quote>> = MutableLiveData()
    val quotes: LiveData<List<Quote>>
        get() = mQuotes

    init {
        GlobalScope.launch {
            getQuotes()
        }
//        mQuotes.value = Utils.quotes
        GlobalScope.launch {
            mQuotes.postValue(getQuotes())
        }

    }


        suspend fun getQuotes(): List<Quote>? {
        val response = RetrofitInstance.QuotesApi.getAllQuotes()
        Log.i(TAG, "getQuotes: " + response.body().toString())
            if (response.isSuccessful) {
                return response.body()
            } else {
                return null
        }
    }
}