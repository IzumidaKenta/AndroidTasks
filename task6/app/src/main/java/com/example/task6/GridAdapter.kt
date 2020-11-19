package com.example.task6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

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

        val message = view?.findViewById<TextView>(R.id.url)
        message?.text = imageItem.url

        return view!!
    }
}