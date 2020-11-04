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

        spinnerFunc(birthdayYearSpinner, R.array.birthday_year_spinner_values)
        spinnerFunc(birthdayMonthSpinner, R.array.birthday_month_spinner_values)
        spinnerFunc(birthdayDaySpinner, R.array.birthday_day_spinner_values)
    }

    private fun spinnerFunc(spinner: Spinner, array: Int) {
        var birthday: String
        var birthdayYear: String
        var birthdayMonth: String
        var birthdayDay: String

        ArrayAdapter.createFromResource(
            this,
            array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                // Kotlin Android Extensions
                when (spinner) {
                    birthday_year_spinner -> {
                        birthdayYear = item
                    }
                    birthday_month_spinner -> {
                        birthdayMonth = item
                    }
                    birthday_day_spinner -> {
                        birthdayDay = item
                    }
                }
                birthday = birthdayYear + "年" + birthdayMonth + "月" + birthdayDay + "日"
                birthday_confirm_text.text = birthday
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                when (spinner) {
                    birthday_year_spinner -> {
                        birthdayYear = "0"
                    }
                    birthday_month_spinner -> {
                        birthdayMonth = "0"
                    }
                    birthday_day_spinner -> {
                        birthdayDay = "0"
                    }
                }
            }
        }
    }
}
