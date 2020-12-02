package com.example.task5

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mMessageList = arrayListOf<Message>()

        //データベースのメッセージを読み込んでListViewを生成
        db.collection("talks").orderBy("id").get()
            .addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
                override fun onComplete(task: Task<QuerySnapshot>) {
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            val message: Message = document.toObject<Message>(Message::class.java)
                            mMessageList.add(message)
                        }
                        val listView = findViewById<ListView>(R.id.listView)
                        val mCustomAdapter = CustomAdapter(context, mMessageList)
                        listView.adapter = mCustomAdapter
                        autoScrollToNewMessage(false)
                    } else {
                        Log.d(
                            "MissionActivity",
                            "Error getting documents: ",
                            task.exception
                        )
                    }
                }
            })

        //送信ボタンタップ時の処理
        sendButton.setOnClickListener {
            if (messageEditText != null) {
                val newMessage = Message(getId(), 1, messageEditText.text.toString(), getNowTime())
                val replyMessage = Message(getId(), 2, randomReply(), getNowTime())
                messageEditText.editableText.clear()
                mMessageList.add(newMessage)
                add(newMessage)
                mMessageList.add(replyMessage)
                add(replyMessage)
                autoScrollToNewMessage(true)
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

    fun getId(): Long {
        val date = Date()
        val format = SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault())
        return format.format(date).toLong()
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
            "id" to this.id,
            "type" to this.type,
            "message" to this.message,
            "sendTime" to this.sendTime
        )
    }

    fun autoScrollToNewMessage(animation: Boolean) {
        //リストアイテムの総数-1（0番目から始まって最後のアイテム）にスクロールさせる
        if (animation) {
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                listView.smoothScrollToPosition(listView.count - 1)
            }, 100)
        } else {
            listView.setSelection(listView.count - 1)
        }
    }
}

