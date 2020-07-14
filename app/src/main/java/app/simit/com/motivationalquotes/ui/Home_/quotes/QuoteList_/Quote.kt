package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_

import org.json.JSONObject

data class Quote(val isApproved: Boolean, val _id: String, val title: String, val imageUrl: String, val hashTags: String, val date: String, val __v: Int) {

    override fun toString(): String {
        var json = JSONObject()
        json.put("isApproved", isApproved)
        json.put("_id", _id)
        json.put("title", title)
        json.put("imageUrl", imageUrl)
        json.put("hashTags", hashTags)
        json.put("date", date)
        json.put("__v", __v)

        return json.toString()

    }

    companion object {
        fun toQuote(jstring: String): Quote {
            var json = JSONObject(jstring)
            
            return Quote(
                    json.getBoolean("isApproved"),
                    json.getString("_id"),
                    json.getString("title"),
                    json.getString("imageUrl"),
                    json.getString("hashTags"),
                    json.getString("date"),
                    json.getInt("__v")
            )
        }
    }

}