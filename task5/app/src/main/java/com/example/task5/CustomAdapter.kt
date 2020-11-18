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

        var view = convertView
        val messageItem = mMessageList[position]
        val resourceId = if (messageItem.type == 1) R.layout.message1 else R.layout.message2

        // レイアウトの設定
        if (view == null) {
            view = layoutInflater.inflate(resourceId, parent, false)
            // このViewのResource IDをTagとして持たせておく
            view.tag = resourceId;
        } else {
            // convertViewがnullでなければResource IDを取得する
            val prevResourceId = view.tag as Int
            if (resourceId != prevResourceId) {
                view = layoutInflater.inflate(resourceId, parent, false)
                view.tag = resourceId
            }
        }

        // 各Viewの設定
        val message = view?.findViewById<TextView>(R.id.message)
        message?.text = messageItem.message

        val sendTime = view?.findViewById<TextView>(R.id.sendTime)
        sendTime?.text = messageItem.sendTime

        return view!!
    }


}