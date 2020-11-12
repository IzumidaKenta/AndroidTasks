package com.example.task4

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class CustomAdapter(context: Context, var mItemList: List<Item>) :
    ArrayAdapter<Item>(context, 0, mItemList) {

    private val layoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Itemの取得
        val item = mItemList[position]

        // レイアウトの設定
        var view = convertView
        if (convertView == null) {
            view = layoutSettings(parent, item.type)
        }

        // 各Viewの設定
        val name = view?.findViewById<TextView>(R.id.name)
        name?.text = item.name

        val phoneNum = view?.findViewById<Button>(R.id.phoneNum)
        phoneNum?.text = item.phoneNum
        phoneNum?.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.phoneNum))
            startActivity(context, intent, null)
        }

        val webPageButton = view?.findViewById<Button>(R.id.webPageButton)
        webPageButton?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://google.co.jp"))
            startActivity(context, intent, null)
        }



        return view!!
    }

    private fun layoutSettings(parent: ViewGroup, type: Int): View? {
        var view: View? = null
        when (type) {
            1 -> {
                view = layoutInflater.inflate(R.layout.item1, parent, false)
            }
            2 -> {
                view = layoutInflater.inflate(R.layout.item2, parent, false)
            }
            3 -> {
                view = layoutInflater.inflate(R.layout.item3, parent, false)
            }
        }
        return view
    }
}