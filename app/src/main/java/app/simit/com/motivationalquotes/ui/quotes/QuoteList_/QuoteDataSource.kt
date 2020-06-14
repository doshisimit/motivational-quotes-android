package app.simit.com.motivationalquotes.ui.quotes.QuoteList_

import androidx.paging.PagingSource
import app.simit.com.motivationalquotes.Api.QuoteCalls
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException

class QuoteDataSource(val quoteCalls: QuoteCalls) : PagingSource<Int, Quote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return try {
            val nextPageNumber = params.key ?: 1

            val response = quoteCalls.getAllQuotes(nextPageNumber, 5)

            LoadResult.Page(
                    data = response,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        }  catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}