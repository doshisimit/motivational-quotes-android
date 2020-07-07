package app.simit.com.motivationalquotes.Api

import app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_.Quote
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface QuoteCalls {

    @GET("quotes/")
    suspend fun getAllQuotes(@Query("page") page: Int, @Query("limit") limit: Int): List<Quote>

}