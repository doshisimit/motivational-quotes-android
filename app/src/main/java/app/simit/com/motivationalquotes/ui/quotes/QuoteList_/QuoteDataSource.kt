package app.simit.com.motivationalquotes.ui.quotes.QuoteList_

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PagingSource
import app.simit.com.motivationalquotes.Api.QuoteCalls
import app.simit.com.motivationalquotes.ui.quotes.QuotesViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class QuoteDataSource(val quoteCalls: QuoteCalls) : PagingSource<Int, Quote>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {

        return try {
            val nextPageNumber = params.key ?: 1

            val response = quoteCalls.getAllQuotes(nextPageNumber,5)

            LoadResult.Page(
                    data = response,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (exception: JSONException) {
            return LoadResult.Error(exception)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }
}