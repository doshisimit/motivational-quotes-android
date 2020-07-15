package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_

import org.json.JSONException
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
            val _id = if (json.isNull("_id")) "No id" else json.getString("_id")
            val _title = if (json.isNull("title")) "No title" else json.getString("title")
            val _image = if (json.isNull("imageUrl")) "No image" else json.getString("imageUrl")
            val _hastag = if (json.isNull("hashTags")) "No hashtag" else json.getString("hashTags")
            val _date = if (json.isNull("date")) "No date" else json.getString("date")

            return Quote(
                    json.getBoolean("isApproved"),
                    _id,
                    _title,
                    _image,
                    _hastag,
                    _date,
                    json.getInt("__v")
            )
        }
    }

}