package app.simit.com.motivationalquotes.Api

import app.simit.com.motivationalquotes.ui.quotes.QuoteList_.Quote
import retrofit2.Response
import retrofit2.http.GET

interface QuoteCalls {

    @GET("quotes/")
    suspend fun getAllQuotes(): Response<List<Quote>>

}