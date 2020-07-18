package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteDetail_

import android.Manifest
import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import app.simit.com.motivationalquotes.R
import app.simit.com.motivationalquotes.data.dao.QuoteDao
import app.simit.com.motivationalquotes.databinding.ActivityQuoteDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject


@AndroidEntryPoint
class QuoteDetailActivity : AppCompatActivity(), EasyPermissions.RationaleCallbacks,
        EasyPermissions.PermissionCallbacks {
    companion object {
        private const val REQ_STORAGE: Int = 320
    }

    private lateinit var binding: ActivityQuoteDetailBinding
    private lateinit var viewModel: QuoteDetailViewModel
    private var fab_open = false

    @Inject
    lateinit var quoteDao: QuoteDao
    private var BookMarkJob: Job? = null

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

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    @AfterPermissionGranted(REQ_STORAGE)
    private fun checkForPermission(url: String, title: String) {
        val permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (EasyPermissions.hasPermissions(this, *permissions)) {

            val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

            val downloadRequest = OneTimeWorkRequestBuilder<DownloadQuoteWorker>()
                    .setInputData(createInputDataForUrl(url, title))
                    .setConstraints(constraints)
                    .build()
            WorkManager.getInstance(this)
                    .enqueue(downloadRequest)

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(downloadRequest.id).observe(this, Observer {
                if (it != null && it.state.isFinished) {
                    val downloadResult = it.outputData.getInt(DownloadQuoteWorker.KEY_RESULT,
                            -1)
                    if (downloadResult == 0) {
                        binding.FabOptionBtn.performClick()
                        Snackbar.make(binding.root, "Download is successful", Snackbar.LENGTH_LONG).show()
                    } else {
                        Snackbar.make(binding.root, "Download failed check your internet connection", Snackbar.LENGTH_LONG).show()
                    }
                }
            })

        } else {
            EasyPermissions.requestPermissions(
                    this,
                    "we need this permission to show the list of recorded video",
                    REQ_STORAGE,
                    *permissions
            )
        }
    }

    private fun createInputDataForUrl(url: String, title: String): Data {
        val builder = Data.Builder()
        url?.let {
            builder.putString(DownloadQuoteWorker.URL, it)
        }
        title?.let {
            builder.putString(DownloadQuoteWorker.TITLE, it)
        }
        return builder.build()
    }

    private fun setVisibility(visiblility: Int) {
        binding.frameLayout.visibility = visiblility
        if (visiblility == View.GONE) {
            binding.fabCopy.hide()
            binding.fabFav.hide()
            binding.fabSaveBtn.hide()
            binding.fabShare.hide()
        } else {
            binding.fabCopy.show()
            binding.fabFav.show()
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

            binding.fabSaveBtn.setOnClickListener { view ->
                if (it.imageUrl.equals("No image")) {
                    Snackbar.make(binding.root, "Quote image url not found", Snackbar.LENGTH_LONG).show()
                } else {
                    checkForPermission(it.imageUrl, it.title)
                }
            }

            binding.fabCopy.setOnClickListener { view ->
                val clipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("quote", it.title + "-" + it.hashTags)
                clipboard.setPrimaryClip(clip)

                binding.FabOptionBtn.performClick()
                Snackbar.make(binding.root, "Quote is copied", Snackbar.LENGTH_LONG).show()
            }

            binding.fabFav.setOnClickListener { view ->
                BookMarkJob?.cancel()
                BookMarkJob = lifecycleScope.launch {
                    viewModel.boomarkQuote(it, quoteDao)
                    Snackbar.make(binding.root, "Quote is saved", Snackbar.LENGTH_LONG).show()
                }
            }
        })

    }

    override fun onRationaleDenied(requestCode: Int) {
    }

    override fun onRationaleAccepted(requestCode: Int) {
    }
}