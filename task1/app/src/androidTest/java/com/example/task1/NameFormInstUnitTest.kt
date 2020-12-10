package com.example.task1

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NameFormInstUnitTest {
    private lateinit var stringToBetyped: String
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)
    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "おはよう"
    }
    @Test
    fun nameFormText_longerThan10characters_ExceptionThrown() {
        onView(withId(R.id.name_form))
            .perform(replaceText(stringToBetyped))
        onView(withId(R.id.button)).perform(click())
        // Check that the text was changed.
        onView(withId(R.id.mail_address_form))
            .check(matches(withText(stringToBetyped)))
    }
}
