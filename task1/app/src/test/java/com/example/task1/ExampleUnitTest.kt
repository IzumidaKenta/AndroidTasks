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
class NameFormUnitTest {
    @Test
    fun nameFormText_longerThan10characters_ExceptionThrown() {
        val activity:MainActivity = Robolectric.buildActivity(MainActivity::class.java).setup().get()
        val textLength = activity.findViewById<EditText>(R.id.name_form).text.length
        assertTrue(textLength < 10)
    }
}
