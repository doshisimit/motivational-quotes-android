package app.simit.com.motivationalquotes.ui.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.simit.com.motivationalquotes.ui.quotes.QuoteList_.Quote
import app.simit.com.motivationalquotes.ui.quotes.QuoteList_.Utils

class QuotesViewModel : ViewModel() {
    private val mQuotes: MutableLiveData<List<Quote>> = MutableLiveData()
    val quotes: LiveData<List<Quote>>
        get() = mQuotes

    init {
        mQuotes.value = Utils.quotes
    }


}