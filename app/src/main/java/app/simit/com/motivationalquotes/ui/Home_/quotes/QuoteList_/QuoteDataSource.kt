package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_

import android.util.Log
import androidx.paging.PagingSource
import app.simit.com.motivationalquotes.Api.QuoteCalls
import org.json.JSONArray
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException

class QuoteDataSource(val quoteCalls: QuoteCalls) : PagingSource<Int, Quote>() {

    private var query: String? = null

    companion object {
        private const val TAG = "QUOTE_DATA_SOURCE"
    }

    constructor(quoteCalls: QuoteCalls, query: String) : this(quoteCalls) {
        this.query = query
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        if (query != null) {

            return try {

                val nextPageNumber = params.key ?: 1
                Log.i(TAG, "load page number: " + nextPageNumber)
                val response = quoteCalls.getSearchQuotes("quotes/search/" + query)
                val array = JSONArray(response)
                Log.i(TAG, "load content: " + array.length())

                LoadResult.Page(
                        data = response,
                        prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                        nextKey = if (response.isEmpty()) null else nextPageNumber + 1
                )

            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            } catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }

        } else {

            return try {

                val nextPageNumber = params.key ?: 1
                Log.i(TAG, "load page number: " + nextPageNumber)
                val response = quoteCalls.getAllQuotes(nextPageNumber, 5)
                val array = JSONArray(response)
                Log.i(TAG, "load content: " + array.toString())

                LoadResult.Page(
                        data = response,
                        prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                        nextKey = if (response.isEmpty()) null else nextPageNumber + 1
                )

            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            } catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }
        }
    }
}