package com.example.task4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    lateinit var mCustomAdapter: CustomAdapter
    lateinit var mItemList: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // データ一覧の実装
        val item1 = Item("営業部1", "01-0000-0000")
        val item2 = Item("営業部3", "01-0000-0000")
        mItemList = arrayListOf(item1, item2)

        val listView = findViewById<ListView>(R.id.listView)

        // CustomAdapterの生成と設定
        mCustomAdapter = CustomAdapter(this, mItemList)
        listView.adapter = mCustomAdapter
    }
}
