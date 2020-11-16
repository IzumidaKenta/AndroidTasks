package com.example.task5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    lateinit var mCustomAdapter: CustomAdapter
    lateinit var mMessageList: ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // データ一覧の実装
        val message1 = Message(1, "おはようございます", "19:00")
        val message2 = Message(2, "Hello", "19:00")
        val message3 = Message(1, "サンプルテキストサンプルテキストサンプルテキスト", "19:05")
        val message4 = Message(2, "Hello", "19:05")
        mMessageList = arrayListOf(message1, message2, message3, message4)

        val listView = findViewById<ListView>(R.id.listView)

        // CustomAdapterの生成と設定
        mCustomAdapter = CustomAdapter(this, mMessageList)
        listView.adapter = mCustomAdapter
    }
}

