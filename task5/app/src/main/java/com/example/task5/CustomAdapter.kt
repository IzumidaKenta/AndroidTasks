package com.example.task5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(context: Context, var mMessageList: List<Message>) :
    ArrayAdapter<Message>(context, 0, mMessageList) {

    private val layoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Itemの取得
        val messageItem = mMessageList[position]

        // レイアウトの設定
        var view = convertView
        if (convertView == null) {
            view = layoutSettings(parent, messageItem.type)
        }

        // 各Viewの設定
        val message = view?.findViewById<TextView>(R.id.message)
        message?.text = messageItem.message

        val sendTime = view?.findViewById<TextView>(R.id.sendTime)
        sendTime?.text = messageItem.sendTime

        return view!!
    }

    private fun layoutSettings(parent: ViewGroup, type: Int): View? {
        var view: View? = null
        when (type) {
            1 -> {
                view = layoutInflater.inflate(R.layout.message1, parent, false)
            }
            2 -> {
                view = layoutInflater.inflate(R.layout.message2, parent, false)
            }
        }
        return view
    }
}