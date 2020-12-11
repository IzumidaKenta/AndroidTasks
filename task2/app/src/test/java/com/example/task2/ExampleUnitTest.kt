package com.example.task2

import android.os.Build
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.get
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
    val activity:MainActivity = Robolectric.buildActivity(MainActivity::class.java).setup().get()

    @Test
    fun nameFormText_longerThan10characters_ExceptionThrown() {
        val nameFormEt: EditText = activity.findViewById<EditText>(R.id.name_form)
        nameFormEt.setText("あいうえお")
        val textLength = nameFormEt.text.length
        assertTrue(textLength < 10)
    }

    @Test
    fun mailAddressFormText_longerThan20characters_ExceptionThrown() {
        val mailAddressFormEt: EditText = activity.findViewById<EditText>(R.id.mail_address_form)
        mailAddressFormEt.setText("test@mail.com")
        val textLength = mailAddressFormEt.text.length
        assertTrue(textLength < 20)
    }

    @Test
    fun birthdaySpinner_equalToBirthdayConfirmText_ExceptionThrown() {
        val confirmText: TextView = activity.findViewById<TextView>(R.id.birthday_confirm_text)
        val birthdayYearSpinner: Spinner = activity.findViewById<Spinner>(R.id.birthday_year_spinner)
        val birthdayMonthSpinner: Spinner = activity.findViewById<Spinner>(R.id.birthday_month_spinner)
        val birthdayDaySpinner: Spinner = activity.findViewById<Spinner>(R.id.birthday_day_spinner)

        val spinnerText: String = "2000年01月01日"
        assertEquals(confirmText.text, spinnerText)
    }

    @Test
    fun addressFormText_longerThan20characters_ExceptionThrown() {
        val addressFormEt: EditText = activity.findViewById<EditText>(R.id.address_form)
        addressFormEt.setText("test@mail.com")
        val textLength = addressFormEt.text.length
        assertTrue(textLength < 20)
    }

    @Test
    fun memoFormText_longerThan30characters_ExceptionThrown() {
        val memoFormEt: EditText = activity.findViewById<EditText>(R.id.memo_form)
        memoFormEt.setText("test@mail.com")
        val textLength = memoFormEt.text.length
        assertTrue(textLength < 20)
    }
}
