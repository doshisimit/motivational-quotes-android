package app.simit.com.motivationalquotes.ui.quotes.QuoteList_

data class Quote(val isApproved: Boolean, val _id: String, val title: String, val imageUrl: String, val hashTags: String, val date: String, val __v: Int) {

    override fun toString(): String {
        return "Quote{" +
                "id=" + _id +
                ", imageURL='" + imageUrl + '\'' +
                ", hashTags='" + hashTags + '\'' +
                ", title='" + title + '\'' +
                '}'
    }

}