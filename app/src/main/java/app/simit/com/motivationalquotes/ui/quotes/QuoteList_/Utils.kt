package app.simit.com.motivationalquotes.ui.quotes.QuoteList_

import java.util.*

object Utils {

    var quotes: ArrayList<Quote>? = null
        private set

    fun initQuotes() {
        if (null == quotes) {
            quotes = ArrayList()
            val quote1 = Quote("1", "https://i.pinimg.com/736x/e9/00/14/e900140f2e8b0c234395ec97748569de.jpg", null, null)
            val quote3 = Quote("3", "https://thumbs.dreamstime.com/b/quote-inspirational-motivational-nquotes-sayings-life-success-nwisdom-positive-uplifting-empowering-nmotivation-144803367.jpg", null, null)
            val quote2 = Quote("2", "https://i.pinimg.com/originals/e7/2f/34/e72f34a74453946af2c6ed1c0b5a0ab1.jpg", null, null)
            val quote4 = Quote("4", "https://image.freepik.com/free-vector/motivational-quote-with-black-background_1020-995.jpg", null, null)
            val quote5 = Quote("5", "https://www.moritzfinedesigns.com/wp-content/uploads/2017/01/motivational-printables_difficult-roads.jpg", null, null)
            val quote6 = Quote("6", "https://image.freepik.com/free-vector/motivational-quote-background_23-2147854384.jpg", null, null)
            val quote7 = Quote("7", "https://cdn.everydaypower.com/wp-content/uploads/2017/03/Inspirational-quote-12.jpg", null, null)
            val quote8 = Quote("8", "https://images.pexels.com/photos/760728/pexels-photo-760728.jpeg?cs=srgb&dl=motivational-quotes-760728.jpg&fm=jpg", null, null)
            val quote9 = Quote("9", "https://image.freepik.com/free-vector/retro-style-motivational-quote-background_23-2147574710.jpg", null, null)
            quotes!!.add(quote1)
            quotes!!.add(quote2)
            quotes!!.add(quote3)
            quotes!!.add(quote4)
            quotes!!.add(quote5)
            quotes!!.add(quote6)
            quotes!!.add(quote7)
            quotes!!.add(quote8)
            quotes!!.add(quote9)
        }
    }
}