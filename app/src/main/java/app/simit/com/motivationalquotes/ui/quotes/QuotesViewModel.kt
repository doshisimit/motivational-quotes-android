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

class QuotesViewModel : ViewModel() {
    private val mQuotes: MutableLiveData<List<Quote>> = MutableLiveData()
    val quotes: LiveData<List<Quote>>
        get() = mQuotes

    init {
//        mQuotes.value = Utils.quotes
        mQuotes.value = getQuotes()

    }


    fun getQuotes():List<Quote>{
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.QuotesApi.getAllQuotes()
            if (response.isSuccessful) {
                val list = response.body()
            }
        }
    }
}