package com.example.task6

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ProgressBar

class GridAdapter(context: Context, var mImageList: List<ImagePass>) :
    ArrayAdapter<ImagePass>(context, 0, mImageList), AsyncTaskCallBacks {

    private val layoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var memoryCache: LruCache<String, Bitmap>
    private var imageUrl = ""

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val imageItem = mImageList[position]
        val resourceId = imageItem.id
        imageUrl = imageItem.url

        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {

            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.byteCount / 1024
            }
        }

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.grid_items, parent, false)
            // このViewのResource IDをTagとして持たせておく
            view.tag = resourceId;
        } else {
            // convertViewがnullでなければResource IDを取得する
            val prevResourceId = view?.tag as Int
            if (resourceId != prevResourceId) {
                view = layoutInflater.inflate(R.layout.grid_items, parent, false)
                view.tag = resourceId
            }
        }

        val image = view?.findViewById<GridViewItem>(R.id.image_view)
        val progressBar = view?.findViewById<ProgressBar>(R.id.progress_bar)
        val cacheImage = memoryCache.get(imageUrl)

        if (cacheImage == null) {
            val loadImageAsyncTask = LoadImageAsyncTask(image, progressBar, this)
            loadImageAsyncTask.execute(imageItem.url)
        } else {
            image?.setImageBitmap(cacheImage)
        }
        return view!!
    }

    public override fun onTaskFinished(result: Bitmap) {
        memoryCache.put(imageUrl, result)
    }
}