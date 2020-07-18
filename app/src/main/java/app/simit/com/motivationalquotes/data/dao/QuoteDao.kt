package app.simit.com.motivationalquotes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import app.simit.com.motivationalquotes.data.entity.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bookmark(quote: Quote)


}