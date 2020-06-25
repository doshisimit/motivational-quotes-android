package app.simit.com.motivationalquotes.ui.quotes.QuoteList_

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.simit.com.motivationalquotes.Api.QuoteCalls
import kotlinx.coroutines.flow.Flow

class QuoteRepository(val quoteCalls: QuoteCalls) {
    fun getSearchResultStream(): Flow<PagingData<Quote>> {
        return Pager(
                config = PagingConfig(pageSize = 5),
                pagingSourceFactory = { QuoteDataSource(quoteCalls) }
        ).flow
    }
}