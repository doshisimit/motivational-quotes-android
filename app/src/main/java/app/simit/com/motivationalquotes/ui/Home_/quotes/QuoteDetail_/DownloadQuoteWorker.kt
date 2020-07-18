package app.simit.com.motivationalquotes.ui.Home_.quotes.QuoteDetail_

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.text.Html
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class DownloadQuoteWorker(val mContext: Context, val mParams: WorkerParameters) : Worker(mContext, mParams) {
    companion object {
        const val URL = "image_url"
        const val TITLE = "title"
        const val KEY_RESULT = "result"
    }

    override fun doWork(): Result {

        try {

            val request = DownloadManager.Request(Uri.parse(inputData.getString(URL)))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            request.setTitle(inputData.getString(TITLE))
            request.setDescription("Downloading your quote")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    System.currentTimeMillis().toString()
            )

            val downloadManager = mContext.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)

            //...set the output, and we're done!
            val output: Data = workDataOf(KEY_RESULT to 0)
            return Result.success(output)

        } catch (e: Exception) {
            val output: Data = workDataOf(KEY_RESULT to -1)
            return Result.failure(output)
        }

    }
}