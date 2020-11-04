package com.example.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val birthdayYearSpinner: Spinner = findViewById(R.id.birthday_year_spinner)
        val birthdayMonthSpinner: Spinner = findViewById(R.id.birthday_month_spinner)
        val birthdayDaySpinner: Spinner = findViewById(R.id.birthday_day_spinner)
        var birthday: String
        var birthdayYear: String = "0"
        var birthdayMonth: String = "0"
        var birthdayDay: String = "0"

        ArrayAdapter.createFromResource(
            this,
            R.array.birthday_year_spinner_values, android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            birthdayYearSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.birthday_month_spinner_values, android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            birthdayMonthSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.birthday_day_spinner_values, android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            birthdayDaySpinner.adapter = adapter
        }

        birthdayYearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                // Kotlin Android Extensions
                birthdayYear = item
                birthday = birthdayYear + "年" + birthdayMonth + "月" + birthdayDay + "日"
                birthday_confirm_text.text = birthday
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                birthdayYear = "0"
            }
        }

        birthdayMonthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                // Kotlin Android Extensions
                birthdayMonth = item
                birthday = birthdayYear + "年" + birthdayMonth + "月" + birthdayDay + "日"
                birthday_confirm_text.text = birthday
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                birthdayMonth = "0"
            }
        }

        birthdayDaySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                // Kotlin Android Extensions
                birthdayDay = item
                birthday = birthdayYear + "年" + birthdayMonth + "月" + birthdayDay + "日"
                birthday_confirm_text.text = birthday
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                birthdayDay = "0"
            }
        }
    }
}
