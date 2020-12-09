package com.example.task1
import android.os.Build
import android.widget.EditText
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class NameFormLocalUnitTest {
    @Test
    fun nameFormText_longerThan10characters_ExceptionThrown() {
        val activity:MainActivity = Robolectric.buildActivity(MainActivity::class.java).setup().get()
        val editText: EditText = activity.findViewById<EditText>(R.id.name_form)
        editText.setText("あいうえお")
        val textLength = editText.text.length
        assertTrue(textLength < 10)
    }
}