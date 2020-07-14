package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteDetail_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.simit.com.motivationalquotes.R
import app.simit.com.motivationalquotes.databinding.ActivityQuoteDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey

class QuoteDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuoteDetailBinding
    private lateinit var viewModel: QuoteDetailViewModel
    private var fab_open = false

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quote_detail)
        setVisibility(View.GONE)
        viewModel = ViewModelProvider(this).get(QuoteDetailViewModel::class.java)


        viewModel.setQuote(intent.getStringExtra(getString(R.string.quote)))

        binding.FabOptionBtn.setOnClickListener {
            if (fab_open) {
                setVisibility(View.GONE)
                binding.FabOptionBtn.setImageDrawable(getDrawable(R.drawable.ic_quote))
            } else {
                setVisibility(View.VISIBLE)
                binding.FabOptionBtn.setImageDrawable(getDrawable(R.drawable.ic_baseline_close_24))
            }
            fab_open = !fab_open
        }
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setVisibility(visiblility: Int) {
        binding.frameLayout.visibility = visiblility
        if (visiblility == View.GONE) {
            binding.fabCopy.hide()
            binding.fabFav.hide()
            binding.fabHashtag.hide()
            binding.fabSaveBtn.hide()
            binding.fabShare.hide()
        } else {
            binding.fabCopy.show()
            binding.fabFav.show()
            binding.fabHashtag.show()
            binding.fabSaveBtn.show()
            binding.fabShare.show()
        }
    }

    override fun onResume() {
        super.onResume()


        viewModel.getQuote().observe(this, Observer {
            var requestOptions: RequestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(ObjectKey(it.imageUrl))
                    .skipMemoryCache(true)
                    .placeholder(R.drawable.ic_logo)

            Glide.with(this)
                    .asBitmap()
                    .load(it.imageUrl)
                    .dontAnimate()
                    .apply(requestOptions)
                    .into(binding.quotePreviewIV)
        })

    }
}