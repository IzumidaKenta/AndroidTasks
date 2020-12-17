package com.example.task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val emailForm: EditText = findViewById(R.id.mail_address_form)
        button.setOnClickListener { emailForm.setText("おはよう") }
    }
}
