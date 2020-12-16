package com.example.task2

import android.os.Build
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ExampleUnitTest {
    private val activity: MainActivity =
        Robolectric.buildActivity(MainActivity::class.java).setup().get()
    private val nameFormEt: EditText = activity.findViewById<EditText>(R.id.name_form)
    private val mailAddressFormEt: EditText =
        activity.findViewById<EditText>(R.id.mail_address_form)
    private val addressFormEt: EditText = activity.findViewById<EditText>(R.id.address_form)
    private val memoFormEt: EditText = activity.findViewById<EditText>(R.id.memo_form)
    var validateLists: ArrayList<MainActivity.CustomClass> = ArrayList()

    val birthdayYearSpinner: Spinner =
        activity.findViewById<Spinner>(R.id.birthday_year_spinner)
    val birthdayMonthSpinner: Spinner =
        activity.findViewById<Spinner>(R.id.birthday_month_spinner)
    val birthdayDaySpinner: Spinner = activity.findViewById<Spinner>(R.id.birthday_day_spinner)

    init {
        validateLists.add(MainActivity.CustomClass("名前", nameFormEt, 10))
        validateLists.add((MainActivity.CustomClass("メールアドレス", mailAddressFormEt, 40)))
        validateLists.add(MainActivity.CustomClass("住所", addressFormEt, 20))
        validateLists.add(MainActivity.CustomClass("メモ", memoFormEt, 30))
    }

    @Test
    fun validateCheckTest_noProblem() {
        nameFormEt.setText("テスト太郎")
        mailAddressFormEt.setText("test@test.com")
        addressFormEt.setText("岐阜県下関市中塚３２−３２")
        memoFormEt.setText("卓球をしたい")

        val expLists: ArrayList<String> = ArrayList()
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun validateCheckTest_name_noTyped() {
        nameFormEt.setText("")
        mailAddressFormEt.setText("test@test.com")
        addressFormEt.setText("岐阜県下関市中塚３２−３２")
        memoFormEt.setText("卓球をしたい")

        val expLists: ArrayList<String> = ArrayList()
        expLists.add("名前入力エラー")
        expLists.add("名前を入力して下さい")
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun validateCheckTest_mailAddress_noTyped() {
        nameFormEt.setText("テスト太郎")
        mailAddressFormEt.setText("")
        addressFormEt.setText("岐阜県下関市中塚３２−３２")
        memoFormEt.setText("卓球をしたい")

        val expLists: ArrayList<String> = ArrayList()
        expLists.add("メールアドレス入力エラー")
        expLists.add("メールアドレスを入力して下さい")
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun validateCheckTest_address_noTyped() {
        nameFormEt.setText("テスト太郎")
        mailAddressFormEt.setText("test@test.com")
        addressFormEt.setText("")
        memoFormEt.setText("卓球をしたい")

        val expLists: ArrayList<String> = ArrayList()
        expLists.add("住所入力エラー")
        expLists.add("住所を入力して下さい")
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun validateCheckTest_memo_noTyped() {
        nameFormEt.setText("テスト太郎")
        mailAddressFormEt.setText("test@test.com")
        addressFormEt.setText("岐阜県下関市中塚３２−３２")
        memoFormEt.setText("")

        val expLists: ArrayList<String> = ArrayList()
        expLists.add("メモ入力エラー")
        expLists.add("メモを入力して下さい")
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun validateCheckTest_limit_name() {
        nameFormEt.setText("テスト太郎テスト太郎テスト太郎テスト太郎")
        mailAddressFormEt.setText("test@test.com")
        addressFormEt.setText("岐阜県下関市中塚３２−３２")
        memoFormEt.setText("卓球をしたい")

        val expLists: ArrayList<String> = ArrayList()
        expLists.add("名前入力エラー")
        expLists.add("10文字の文字数制限を超えています")
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun validateCheckTest_limit_emailAddress() {
        nameFormEt.setText("テスト太郎")
        mailAddressFormEt.setText("test@test.comtest@test.comtest@test.comtest@test.comtest@test.comtest@test.comtest@test.com")
        addressFormEt.setText("岐阜県下関市中塚３２−３２")
        memoFormEt.setText("卓球をしたい")

        val expLists: ArrayList<String> = ArrayList()
        expLists.add("メールアドレス入力エラー")
        expLists.add("40文字の文字数制限を超えています")
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun validateCheckTest_limit_address() {
        nameFormEt.setText("テスト太郎")
        mailAddressFormEt.setText("test@test.com")
        addressFormEt.setText("岐阜県下関市中塚３２−３２岐阜県下関市中塚３２−３２岐阜県下関市中塚３２−３２岐阜県下関市中塚３２−３２")
        memoFormEt.setText("卓球をしたい")

        val expLists: ArrayList<String> = ArrayList()
        expLists.add("住所入力エラー")
        expLists.add("20文字の文字数制限を超えています")
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun validateCheckTest_limit_memo() {
        nameFormEt.setText("テスト太郎")
        mailAddressFormEt.setText("test@test.com")
        addressFormEt.setText("岐阜県下関市中塚３２−３２")
        memoFormEt.setText("卓球をしたい卓球をしたい卓球をしたい卓球をしたい卓球をしたい卓球をしたい卓球をしたい卓球をしたい卓球をしたい卓球をしたい卓球をしたい")

        val expLists: ArrayList<String> = ArrayList()
        expLists.add("メモ入力エラー")
        expLists.add("30文字の文字数制限を超えています")
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun validateCheckTest_emailAddress() {
        nameFormEt.setText("テスト太郎")
        mailAddressFormEt.setText("dfkadfms;dflsalfa")
        addressFormEt.setText("岐阜県下関市中塚３２−３２")
        memoFormEt.setText("卓球をしたい")

        val expLists: ArrayList<String> = ArrayList()
        expLists.add("メールアドレスエラー")
        expLists.add("正しいメールアドレスを入力して下さい")
        val retList = activity.validateCheck(validateLists)

        assertTrue(expLists == retList)
    }

    @Test
    fun birthdaySpinner_Existence_ExceptionThrown1() {
        //2019年2月29日
        birthdayYearSpinner.setSelection(19)
        birthdayMonthSpinner.setSelection(1)
        birthdayDaySpinner.setSelection(28)

        assertTrue(
            activity.spinnerCheck(
                birthdayYearSpinner,
                birthdayMonthSpinner,
                birthdayDaySpinner
            ) == false
        )
    }

    @Test
    fun birthdaySpinner_Existence_ExceptionThrown2() {
        //2019年4月31日
        birthdayYearSpinner.setSelection(19)
        birthdayMonthSpinner.setSelection(3)
        birthdayDaySpinner.setSelection(30)

        assertTrue(
            activity.spinnerCheck(
                birthdayYearSpinner,
                birthdayMonthSpinner,
                birthdayDaySpinner
            ) == false
        )
    }

    @Test
    fun birthdaySpinner_Existence_noProblem_leapYear() {
        //2020年2月29日(閏年)
        birthdayYearSpinner.setSelection(20)
        birthdayMonthSpinner.setSelection(1)
        birthdayDaySpinner.setSelection(28)

        assertTrue(
            activity.spinnerCheck(
                birthdayYearSpinner,
                birthdayMonthSpinner,
                birthdayDaySpinner
            )
        )
    }

    @Test
    fun birthdaySpinner_Existence_noProblem() {
        //2020年4月1日
        birthdayYearSpinner.setSelection(20)
        birthdayMonthSpinner.setSelection(3)
        birthdayDaySpinner.setSelection(1)

        assertTrue(
            activity.spinnerCheck(
                birthdayYearSpinner,
                birthdayMonthSpinner,
                birthdayDaySpinner
            )
        )
    }
}
