package com.example.task4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    lateinit var mCustomAdapter: CustomAdapter
    lateinit var mItemList: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // データ一覧の実装
        val item1 = Item(1, "営業部1", null, "01-0000-0000")
        val item2 = Item(2, "営業部2", null, null)
        val item3 = Item(1, "営業部3", null, "02-0000-0000")
        val item4 = Item(3, "営業部4", "平日、土日祝日 9:00〜18:00 (年末年始を除く)", null)
        mItemList = arrayListOf(item1, item2, item3, item4)

        val listView = findViewById<ListView>(R.id.listView)
        val header: View = View.inflate(this, R.layout.header, null)
        listView.addHeaderView(header, null, false)

        // CustomAdapterの生成と設定
        mCustomAdapter = CustomAdapter(this, mItemList)
        listView.adapter = mCustomAdapter
    }
}
