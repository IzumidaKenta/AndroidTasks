package com.example.task7

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.task7.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val listener: View.OnTouchListener = View.OnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }
        binding.memoForm.setOnTouchListener(listener)
        binding.addressForm.setOnTouchListener(listener)
    }


//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(binding.scrollView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
//        binding.scrollView.requestFocus()
//        return super.dispatchTouchEvent(ev)
//    }

    class CustomClass(val name: String, val editText: EditText, val length: Int) {
    }


}