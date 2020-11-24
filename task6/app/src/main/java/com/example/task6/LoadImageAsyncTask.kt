package com.example.task6

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import java.io.InputStream
import java.net.URL


class LoadImageAsyncTask(
    image: GridViewItem?,
    progressBar: ProgressBar?
) : AsyncTask<String, Void, Void>() {

    //メソッドdoInBackgoundの返り値をBitmapには出来ないのか。
    override fun doInBackground(vararg p0: String?): Bitmap {
        val url = URL(p0[0])
        val tIstream: InputStream = url.openStream()
        return BitmapFactory.decodeStream(tIstream);
    }

    //どのようにしたらこの場所でimageとprogressBarを使えるのか
    //doInBackgroundで取得したBitmapをどのようにしたらonPostExecuteでセットできるのか
    override fun onPostExecute(result: Void) {
        image.setImageBitmap(Bitmap)
        progressBar?.visibility = View.GONE
    }


}