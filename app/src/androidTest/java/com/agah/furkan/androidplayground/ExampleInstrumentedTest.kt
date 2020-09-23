package com.agah.furkan.androidplayground

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agah.furkan.androidplayground.ui.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var mActivityTestRule : ActivityScenarioRule<MainActivity> = ActivityScenarioRule (MainActivity::class.java)

    @Test
    fun testView(){
        onView(ViewMatchers.withId(R.id.main_screen_pokemon_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}