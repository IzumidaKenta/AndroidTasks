package com.example.task6

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import java.io.InputStream
import java.net.URL


class LoadImageAsyncTask(
    private val image: GridViewItem?,
    private val progressBar: ProgressBar?
) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg p0: String?): Bitmap {
        val url = URL(p0[0])
        val tIstream: InputStream = url.openStream()
        val bitmap = BitmapFactory.decodeStream(tIstream)
        BitmapCache.putBitmap(p0[0], bitmap)
        return bitmap
    }

    override fun onPostExecute(result: Bitmap) {
        image?.setImageBitmap(result)
        progressBar?.visibility = View.GONE
    }
}