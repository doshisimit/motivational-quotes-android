package app.simit.com.motivationalquotes;

import java.util.ArrayList;

public class Utils {
    public static ArrayList<Quote> getQuotes() {
        return quotes;
    }

    private static ArrayList<Quote> quotes;

    public static void initQuotes() {
        if (null == quotes){


            quotes = new ArrayList<>();
            Quote quote1 = new Quote("1", "https://i.pinimg.com/736x/e9/00/14/e900140f2e8b0c234395ec97748569de.jpg",null,null);
            Quote quote3 = new Quote("3", "https://thumbs.dreamstime.com/b/quote-inspirational-motivational-nquotes-sayings-life-success-nwisdom-positive-uplifting-empowering-nmotivation-144803367.jpg",null,null);
            Quote quote2 = new Quote("2", "https://i.pinimg.com/originals/e7/2f/34/e72f34a74453946af2c6ed1c0b5a0ab1.jpg",null,null);
            Quote quote4 = new Quote("4", "https://image.freepik.com/free-vector/motivational-quote-with-black-background_1020-995.jpg",null,null);
            Quote quote5 = new Quote("5", "https://www.moritzfinedesigns.com/wp-content/uploads/2017/01/motivational-printables_difficult-roads.jpg",null,null);
            Quote quote6 = new Quote("6", "https://image.freepik.com/free-vector/motivational-quote-background_23-2147854384.jpg",null,null);
            Quote quote7 = new Quote("7", "https://cdn.everydaypower.com/wp-content/uploads/2017/03/Inspirational-quote-12.jpg",null,null);
            Quote quote8 = new Quote("8", "https://images.pexels.com/photos/760728/pexels-photo-760728.jpeg?cs=srgb&dl=motivational-quotes-760728.jpg&fm=jpg",null,null);
            Quote quote9 = new Quote("9", "https://image.freepik.com/free-vector/retro-style-motivational-quote-background_23-2147574710.jpg",null,null);
            quotes.add(quote1);
            quotes.add(quote2);
            quotes.add(quote3);
            quotes.add(quote4);
            quotes.add(quote5);
            quotes.add(quote6);
            quotes.add(quote7);
            quotes.add(quote8);
            quotes.add(quote9);
        }
    }
}
