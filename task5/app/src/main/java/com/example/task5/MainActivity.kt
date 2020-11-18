package com.example.task5

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mCustomAdapter: CustomAdapter
    val db = FirebaseFirestore.getInstance()

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
                add(newMessage)
                mMessageList.add(replyMessage)
                add(replyMessage)
                //リストアイテムの総数-1（0番目から始まって最後のアイテム）にスクロールさせる
                Handler(Looper.getMainLooper()).postDelayed(Runnable {
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

    fun add(message: Message) {
        db.collection("talks")
            .add(message.toMap())
            .addOnSuccessListener { documentReference ->
                Log.d("add", "DocumentSnapshot added : ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("add", "Error adding document", e)
            }
    }

    fun Message.toMap(): Map<String, *> {
        return hashMapOf(
            "type" to this.type,
            "message" to this.message,
            "sendTime" to this.sendTime
        )
    }
}

