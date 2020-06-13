package app.simit.com.motivationalquotes.ui.quotes

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import app.simit.com.motivationalquotes.Api.QuoteCalls
import app.simit.com.motivationalquotes.Utils_.Prefrence
import app.simit.com.motivationalquotes.ui.quotes.QuoteList_.Quote
import app.simit.com.motivationalquotes.ui.quotes.QuoteList_.QuoteRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesViewModel(val mState: SavedStateHandle) : ViewModel() {

    companion object {
        private const val TAG: String = "QUOTES_VIEW_MODEL"
        private const val QUOTE_POS: String = "QUOTES_POS"
    }

    lateinit var quoteCalls: QuoteCalls

    private var currentSearchResult: Flow<PagingData<Quote>>? = null
    private lateinit var repo: QuoteRepository

    private val mQuotes: MutableLiveData<List<Quote>> = MutableLiveData()
    val quotes: LiveData<List<Quote>>
        get() = mQuotes

    fun getQuotes(): Flow<PagingData<Quote>>? {
        return currentSearchResult
    }

    fun getQuotePos(): Parcelable? {
        return mState.get<Parcelable>(QUOTE_POS)
    }

    fun setService(quoteCallService: QuoteCalls) {
        quoteCalls = quoteCallService
        repo = QuoteRepository(quoteCalls)
        currentSearchResult = repo.getSearchResultStream()
    }

    fun setQuotePos(Pos: Parcelable?) {
        mState.set(QUOTE_POS, Pos)
    }

}