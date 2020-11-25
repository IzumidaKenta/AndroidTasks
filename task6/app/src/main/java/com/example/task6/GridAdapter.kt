package com.example.task6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ProgressBar


class GridAdapter(context: Context, var mImageList: List<ImagePass>) :
    ArrayAdapter<ImagePass>(context, 0, mImageList) {

    private val layoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val imageItem = mImageList[position]
        val resourceId = imageItem.id

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

        val loadImageAsyncTask = LoadImageAsyncTask(image, progressBar)
        loadImageAsyncTask.execute(imageItem.url)
        return view!!
    }
}