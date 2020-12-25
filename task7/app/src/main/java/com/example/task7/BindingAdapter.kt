package com.example.task7

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("entries")
fun entries(recyclerView: RecyclerView?, array: Array<String?>?) {
    //get all values in array[] variable.
}

@BindingAdapter("text")
fun setTextViewText(textView: TextView, text: String?) {
    if (text != null) {
        textView.text = text
    } else {
        textView.text = ""
    }
}
