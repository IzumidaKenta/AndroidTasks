package com.example.task6

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import kotlinx.coroutines.async
import java.io.InputStream
import java.net.URL


class GridAdapter(context: Context, var mImageList: List<ImagePass>) :
    ArrayAdapter<ImagePass>(context, 0, mImageList) {

    private val layoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val imageItem = mImageList[position]

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.grid_items, parent, false)
        }

        val image = view?.findViewById<GridViewItem>(R.id.image_view)
        val progressBar = view?.findViewById<ProgressBar>(R.id.progress_bar)

        async {
            val url = URL(imageItem.url)
            val tIstream: InputStream = url.openStream()
            val mBitmap = BitmapFactory.decodeStream(tIstream);
            image?.setImageBitmap(mBitmap)
            progressBar?.visibility = View.GONE
        }

        return view!!
    }
}