package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteDetail_

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_.Quote

class QuoteDetailViewModel : ViewModel() {

    private var quote: MutableLiveData<Quote> = MutableLiveData()

    fun setQuote(stringQuote: String): Unit {
        quote.postValue(Quote.toQuote(stringQuote))
    }

    fun getQuote(): LiveData<Quote> {
        return quote
    }
}