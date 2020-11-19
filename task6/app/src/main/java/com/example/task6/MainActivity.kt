package com.example.task6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageList = arrayListOf<String>()
        imageList.add("https:1")
        imageList.add("https:2")
        imageList.add("https:3")
        imageList.add("https:4")
        imageList.add("https:5")
        imageList.add("https:6")
        imageList.add("https:7")
        imageList.add("https:8")
        imageList.add("https:9")

        val gridAdapter = GridAdapter(this, imageList)
        gridView.adapter = gridAdapter
    }
}
