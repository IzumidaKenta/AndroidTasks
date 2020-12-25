package com.example.task7

import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class MainViewModel : ViewModel() {

    private val _birthdayYearSelected = MutableLiveData("2000")
    private val _birthdayMonthSelected = MutableLiveData("01")
    private val _birthdayDaySelected = MutableLiveData("01")

    val birthdayYearSelected: LiveData<String> = _birthdayYearSelected
    val birthdayMonthSelected: LiveData<String> = _birthdayMonthSelected
    val birthdayDaySelected: LiveData<String> = _birthdayDaySelected

    private val validateLists: ArrayList<MainActivity.CustomClass> = MutableLiveData<ArrayList>(CustomClass("名前", , 10))


    fun tapSendButton() {
        validateLists.add(CustomClass("名前", binding.nameForm, 10))
        validateLists.add((CustomClass("メールアドレス", binding.mailAddressForm, 40)))
        validateLists.add(CustomClass("住所", binding.addressForm, 20))
        validateLists.add(CustomClass("メモ", binding.memoForm, 30))
        val ret = validateCheck(validateLists)
        if (ret.isNotEmpty()) {
            alertDialog(ret[0], ret[1])
            return
        }
        if (!spinnerCheck(
                binding.birthdayYearSpinner,
                binding.birthdayMonthSpinner,
                binding.birthdayDaySpinner
            )
        ) {
            alertDialog("誕生日エラー", "存在しない日です")
            return
        }
        Toast.makeText(applicationContext, "送信完了", Toast.LENGTH_LONG).show()
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

    fun validateCheck(lists: ArrayList<CustomClass>): List<String> {
        val ret: ArrayList<String> = ArrayList()
        for (list in lists) {
            if (!isTyped(list.editText)) {
                ret.add(list.name + "入力エラー")
                ret.add(list.name + "を入力して下さい")
                return ret
            }
            if (!textLimit(list.editText.text.toString(), list.length)) {
                ret.add(list.name + "入力エラー")
                ret.add(list.length.toString() + "文字の文字数制限を超えています")
                return ret
            }
            if (list.name == "メールアドレス") {
                if (!mailValidation(list.editText.text.toString())) {
                    ret.add(list.name + "エラー")
                    ret.add("正しいメールアドレスを入力して下さい")
                    return ret
                }
            }
        }
        return ret
    }

    class CustomClass(val name: String, val editText: EditText, val length: Int) {
    }

}

