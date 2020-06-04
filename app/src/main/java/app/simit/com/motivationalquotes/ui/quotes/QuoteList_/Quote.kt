package app.simit.com.motivationalquotes.ui.quotes.QuoteList_

data class Quote(var id: String, var imageURL: String, var hashTags: String?, var title: String?) {

    override fun toString(): String {
        return "Quote{" +
                "id=" + id +
                ", imageURL='" + imageURL + '\'' +
                ", hashTags='" + hashTags + '\'' +
                ", title='" + title + '\'' +
                '}'
    }

}