package app.simit.com.motivationalquotes.data.repository

import android.app.Application
import app.simit.com.motivationalquotes.data.dao.QuoteDao
import app.simit.com.motivationalquotes.data.entity.Quote

class QuoteRepo(val dao: QuoteDao) {

    public fun bookMarkQuote(quote: Quote) {
        dao.bookmark(quote)
    }
}