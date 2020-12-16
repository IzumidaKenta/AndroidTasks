package com.example.task2

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    var birthday: String = ""
    var birthdayYear: String = "0"
    var birthdayMonth: String = "0"
    var birthdayDay: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val birthdayYearSpinner: Spinner = findViewById(R.id.birthday_year_spinner)
        val birthdayMonthSpinner: Spinner = findViewById(R.id.birthday_month_spinner)
        val birthdayDaySpinner: Spinner = findViewById(R.id.birthday_day_spinner)

        spinnerDataSetFunc(birthdayYearSpinner, R.array.birthday_year_spinner_values)
        spinnerDataSetFunc(birthdayMonthSpinner, R.array.birthday_month_spinner_values)
        spinnerDataSetFunc(birthdayDaySpinner, R.array.birthday_day_spinner_values)

        val listener: View.OnTouchListener = View.OnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }
        memo_form.setOnTouchListener(listener)
        address_form.setOnTouchListener(listener)

        send_button.setOnClickListener {
            val ret = validateCheck()
            if (ret.isNotEmpty()) {
                alertDialog(ret[0], ret[1])
                return@setOnClickListener
            }
            if (!spinnerCheck(
                    birthday_year_spinner,
                    birthday_month_spinner,
                    birthday_day_spinner
                )
            ) {
                alertDialog("誕生日エラー", "存在しない日です")
                return@setOnClickListener
            }
            Toast.makeText(applicationContext, "送信完了", Toast.LENGTH_LONG).show()
        }
    }

    private fun spinnerDataSetFunc(spinner: Spinner, array: Int) {
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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(scrollView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        scrollView.requestFocus()
        return super.dispatchTouchEvent(ev)
    }

    fun textLimit(text: String, limit: Int): Boolean {
        return text.length < limit
    }

    fun isTyped(editText: EditText): Boolean {
        return editText.text.toString().isNotEmpty()
    }

    private fun mailValidation(text: String): Boolean {
        val pattern =
            "^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$"
        val p: Pattern = Pattern.compile(pattern)
        return p.matcher(text).find()
    }

    fun spinnerCheck(
        birthday_year_spinner: Spinner,
        birthday_month_spinner: Spinner,
        birthday_day_spinner: Spinner
    ): Boolean {

        var result = true
        val birthdayYearString: String = birthday_year_spinner.selectedItem as String
        val birthdayMonthString: String = birthday_month_spinner.selectedItem as String
        val birthdayDayString: String = birthday_day_spinner.selectedItem as String

        if (isLeapYear(birthdayYearString) && birthdayMonthString == "02") {
            if (birthdayDayString == "30" || birthdayDayString == "31") {
                result = false
            }
        } else {
            if (birthdayDayString == "29" || birthdayDayString == "30" || birthdayDayString == "31") {
                result = false
            }
        }

        if (birthdayMonthString == "04" && birthdayDayString == "31") {
            result = false
        } else if (birthdayMonthString == "06" && birthdayDayString == "31") {
            result = false
        } else if (birthdayMonthString == "09" && birthdayDayString == "31") {
            result = false
        } else if (birthdayMonthString == "11" && birthdayDayString == "31") {
            result = false
        }

        return result
    }

    private fun isLeapYear(year: String): Boolean {
        val year = year.toInt()
        var isLeapYear: Boolean = false
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    isLeapYear = true // うるう年
                }
            } else {
                isLeapYear = true // うるう年
            }
        }
        return isLeapYear
    }

    private fun alertDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }

    private fun validateCheck(): List<String> {
        val validateLists: List<Boolean> = listOf(
            isTyped(name_form),
            isTyped(mail_address_form),
            isTyped(address_form),
            isTyped(memo_form),
            textLimit(name_form.text.toString(), 10),
            textLimit(mail_address_form.text.toString(), 40),
            textLimit(address_form.text.toString(), 20),
            textLimit(memo_form.text.toString(), 30),
            mailValidation(mail_address_form.text.toString())
        )

        val alertTitleList: List<String> = listOf(
            "名前未入力", "メールアドレス未入力", "住所未入力", "メモ未入力", "名前入力エラー", "メールアドレス入力エラー", "住所入力エラー",
            "メモ入力エラー", "メールアドレスエラー"
        )
        val alertMessageList: List<String> = listOf(
            "名前を入力して下さい",
            "メールアドレスを入力して下さい",
            "住所を入力して下さい",
            "メモを入力して下さい",
            "10文字の文字数制限を超えています",
            "40文字の文字数制限を超えています",
            "20文字の文字数制限を超えています",
            "30文字の文字数制限を超えています",
            "正しいメールアドレスを入力して下さい"
        )

        var count = 0
        for (item in validateLists) {
            if (!item) {
                return listOf(alertTitleList[count], alertMessageList[count])
            }
            count += 1
        }
        return listOf()
    }
}


