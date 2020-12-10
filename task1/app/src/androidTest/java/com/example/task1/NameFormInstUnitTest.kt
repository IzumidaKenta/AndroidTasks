package com.example.task1

import android.widget.EditText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NameFormInstUnitTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun nameFormText_longerThan10characters_ExceptionThrown() {
        val activity: MainActivity = activityRule.activity
        val editText: EditText = activity.findViewById<EditText>(R.id.name_form)
        val textLength = editText.text.length
        assertTrue(textLength < 10)
    }
}
