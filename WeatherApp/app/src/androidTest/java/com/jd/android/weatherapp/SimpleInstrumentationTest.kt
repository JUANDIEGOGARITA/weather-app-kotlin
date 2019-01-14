package com.jd.android.weatherapp

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.jd.android.weatherapp.activity.MainActivity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test

class SimpleInstrumentationTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun itemClickNavigationToDetail() {
        onView(withId(R.id.forecastList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.weatherDescription))
            .check(matches(isAssignableFrom(TextView::class.java)))
    }

    @Test
    fun modifyZipCodeChangesToolbarTitle() {
        openActionBarOverflowOrOptionsMenu(activityRule.activity)
        onView(withId(R.string.settings)).perform(click())

        onView(withId(R.id.cityCode)).perform(replaceText("94301"))

        pressBack()

        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(`is`("Palo Alto (US)"))))
    }

    private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> =
        object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {

            override fun matchesSafely(toolbar: Toolbar): Boolean =
                textMatcher.matches(toolbar.title)

            override fun describeTo(description: Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }

}