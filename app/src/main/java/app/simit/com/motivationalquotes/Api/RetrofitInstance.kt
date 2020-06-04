package app.simit.com.motivationalquotes.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        val BASE_URL = "https://quiet-sierra-93508.herokuapp.com/"

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val QuotesApi = retrofit.create(QuoteCalls::class.java)
    }
}