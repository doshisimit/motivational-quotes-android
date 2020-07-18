package app.simit.com.motivationalquotes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import app.simit.com.motivationalquotes.data.dao.QuoteDao
import app.simit.com.motivationalquotes.data.entity.Quote
import kotlin.reflect.KClass

@Database(entities = arrayOf(Quote::class), version = 0)
public abstract class db : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
    
}