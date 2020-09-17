package com.kush.letsgetchecked

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kush.letsgetchecked.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Kush on 17/09/2020.
 */
@RunWith(AndroidJUnit4::class)
class SearchTextEspresso {

    @Rule @JvmField
    var loginActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(
        MainActivity::class.java, true
    )

    @Test
    fun searchText() {

        onView(ViewMatchers.withId(R.id.action_country_search))
            .perform(ViewActions.typeText("Albania"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(ViewMatchers.withId(R.id.country_RV))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(ViewMatchers.withId(R.id.country_selected_name_value)).check(matches(withText("Albania")))

    }

}