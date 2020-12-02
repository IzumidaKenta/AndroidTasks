package com.example.task6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val context = this
    private val mImageList = arrayListOf<ImagePass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeGridView()

    }

    private fun makeGridView() {
        //データベースのimagePassを読み込んでGridViewを生成
        db.collection("imagePass").orderBy("id").get()
            .addOnCompleteListener { task ->
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
    }
}
