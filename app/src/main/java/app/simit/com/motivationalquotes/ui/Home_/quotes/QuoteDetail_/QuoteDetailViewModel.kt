package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteDetail_

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.simit.com.motivationalquotes.data.dao.QuoteDao
import app.simit.com.motivationalquotes.data.repository.QuoteRepo
import app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_.Quote

class QuoteDetailViewModel : ViewModel() {

    private var quote: MutableLiveData<Quote> = MutableLiveData()

    fun setQuote(stringQuote: String): Unit {
        quote.postValue(Quote.toQuote(stringQuote))
    }

    fun getQuote(): LiveData<Quote> {
        return quote
    }

    fun boomarkQuote(quote: Quote, dao: QuoteDao) {
        val repo = QuoteRepo(dao)
        repo.bookMarkQuote(app.simit.com.motivationalquotes.data.entity.Quote(quote.isApproved, quote._id, quote.title, quote.imageUrl, quote.hashTags, quote.date, quote.__v))
    }
}