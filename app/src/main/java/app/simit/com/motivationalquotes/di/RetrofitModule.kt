package app.simit.com.motivationalquotes.di

import android.content.Context
import app.simit.com.motivationalquotes.Api.QuoteCalls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(@ApplicationContext mContext: Context): Retrofit {
        //val BASE_URL = "https://quiet-sierra-93508.herokuapp.com/"
        val BASE_URL = "https://afternoon-taiga-39393.herokuapp.com/"

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideQuoteCall(retrofit: Retrofit): QuoteCalls {
        return retrofit.create(QuoteCalls::class.java)
    }
}