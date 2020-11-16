package com.example.task5

import android.os.Bundle
import android.os.Handler
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var mCustomAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mMessageList = arrayListOf<Message>()
        val listView = findViewById<ListView>(R.id.listView)

        // CustomAdapterの生成と設定
        mCustomAdapter = CustomAdapter(this, mMessageList)
        listView.adapter = mCustomAdapter


        //送信ボタンタップ時の処理
        sendButton.setOnClickListener {
            if (messageEditText != null) {
                val newMessage = Message(1, messageEditText.text.toString(), getNowTime())
                val replyMessage = Message(2, randomReply(), getNowTime())
                messageEditText.editableText.clear()
                mMessageList.add(newMessage)
                mMessageList.add(replyMessage)
                //リストアイテムの総数-1（0番目から始まって最後のアイテム）にスクロールさせる
                Handler().postDelayed(Runnable {
                    listView.smoothScrollToPosition(listView.count - 1)
                }, 100)
            }
        }
    }

    fun randomReply(): String {
        val replyList = arrayListOf<String>("Hello", "GOODBYE", "THANK YOU", "GOOD", "YEAH")
        val index = Random().nextInt(replyList.size)
        return replyList[index]
    }

    fun getNowTime(): String {
        val date = Date()
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        return format.format(date)
    }
}

