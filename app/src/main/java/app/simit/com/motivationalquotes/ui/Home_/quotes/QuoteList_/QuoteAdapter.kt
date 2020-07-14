package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
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


class QuoteAdapter(private val context: Context, private val listener: QuoteClickListener) : PagingDataAdapter<Quote, RecyclerView.ViewHolder>(Quote_COMPARATOR) {
    companion object {
        private const val TAG: String = "QUOTE_ADAPTER"

        private val Quote_COMPARATOR = object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean =
                    oldItem.imageUrl == newItem.imageUrl

            override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean =
                    oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuoteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val quote = getItem(position)
        (holder as QuoteViewHolder).bind(context, quote,listener)
    }

}