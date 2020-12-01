package com.example.task6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mImageList = arrayListOf<ImagePass>()

        //データベースのメッセージを読み込んでListViewを生成
        db.collection("imagePass").orderBy("id").get()
            .addOnCompleteListener(object : OnCompleteListener<QuerySnapshot> {
                override fun onComplete(task: Task<QuerySnapshot>) {
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            val imagePass: ImagePass =
                                document.toObject<ImagePass>(ImagePass::class.java)
                            mImageList.add(imagePass)
                        }
                        val gridAdapter = GridAdapter(context, mImageList)
                        gridView.adapter = gridAdapter
                    } else {
                        Log.d(
                            "MissionActivity",
                            "Error getting documents: ",
                            task.exception
                        )
                    }
                }
            })

    }
}
