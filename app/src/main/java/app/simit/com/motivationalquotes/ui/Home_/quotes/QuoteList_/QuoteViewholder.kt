package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteList_

import android.content.Context
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
import com.bumptech.glide.signature.ObjectKey

class QuoteViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    private val parent: CardView = itemView.findViewById(R.id.parent)
    private val image: ImageView = itemView.findViewById(R.id.img)

    private var quote: Quote? = null

    fun bind(mContext: Context, quote: Quote?) {
        if (quote != null) {
            image.layout(0, 0, 0, 0)
            var requestOptions: RequestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(ObjectKey(quote.imageUrl))
                    .skipMemoryCache(true)
                    .placeholder(R.drawable.ic_logo)

            Glide.with(mContext)
                    .asBitmap()
                    .load(quote.imageUrl)
                    .dontAnimate()
                    .apply(requestOptions)
                    .into(image)
        } else {
            // TODO: 6/9/2020 loading code
        }
    }

    companion object {
        fun create(parent: ViewGroup): QuoteViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
            return QuoteViewHolder(view)
        }
    }
}
