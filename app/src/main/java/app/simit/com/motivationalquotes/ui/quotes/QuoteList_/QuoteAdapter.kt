package app.simit.com.motivationalquotes.ui.quotes.QuoteList_

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import app.simit.com.motivationalquotes.R
import com.bumptech.glide.Glide
import java.util.*

class QuoteAdapter(private val context: Context) : RecyclerView.Adapter<QuoteAdapter.ViewHolder>() {
    private var quotes = ArrayList<Quote>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).asBitmap().load(quotes[position].imageURL).into(holder.image)
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

}