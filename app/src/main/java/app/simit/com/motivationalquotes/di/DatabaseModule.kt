package app.simit.com.motivationalquotes.di

import android.content.Context
import androidx.room.Room
import app.simit.com.motivationalquotes.data.dao.QuoteDao
import app.simit.com.motivationalquotes.data.db.db
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
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): db {
        return Room.databaseBuilder(
                appContext,
                db::class.java,
                "Quote_db"
        ).build()
    }

    @Provides
    fun provideQuoteDao(database: db): QuoteDao {
        return database.quoteDao()
    }
}