package com.example.task4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(context: Context, var mItemList: List<Item>) : ArrayAdapter<Item>(context, 0, mItemList) {

    private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Itemの取得
        val item = mItemList[position]

        // レイアウトの設定
        var view = convertView
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item1, parent, false)
        }

        // 各Viewの設定
        val name = view?.findViewById<TextView>(R.id.name)
        name?.text = item.name

        val phoneNum = view?.findViewById<TextView>(R.id.phoneNum)
        phoneNum?.text = item.phoneNum

        return view!!
    }
}