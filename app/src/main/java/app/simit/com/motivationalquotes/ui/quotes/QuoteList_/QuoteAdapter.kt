package app.simit.com.motivationalquotes.ui.quotes.QuoteList_

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import app.simit.com.motivationalquotes.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL
import java.util.*


class QuoteAdapter(private val context: Context) : RecyclerView.Adapter<QuoteAdapter.ViewHolder>() {
    companion object {
        private const val TAG: String = "QUOTE_ADAPTER"
    }

    private var quotes = ArrayList<Quote>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "id: ${quotes[position]._id} url : ${quotes[position].imageUrl}")

        holder.image.layout(0, 0, 0, 0)
        var requestOptions: RequestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(ObjectKey(Date()))

        Glide.with(context)
                .asBitmap()
                .load(quotes[position].imageUrl)
                .dontAnimate()
                .apply(requestOptions)
                .into(holder.image)

    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val parent: CardView
        val image: ImageView

        init {
            parent = itemView.findViewById(R.id.parent)
            image = itemView.findViewById(R.id.img)
        }
    }

    fun setQuotes(quotes: List<Quote>) {
        this.quotes = quotes as ArrayList<Quote>
        notifyDataSetChanged()
    }

    suspend fun LoadImageFromWebOperations(url: String?): Drawable? {
        return try {
            val `is`: InputStream = URL(url).content as InputStream
            Drawable.createFromStream(`is`, "src name")
        } catch (e: Exception) {
            null
        }

    }
}