package com.agah.furkan.androidplayground

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agah.furkan.androidplayground.ui.MainActivity
import com.agah.furkan.androidplayground.util.CountingIdlingResourceSingleton
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var mActivityTestRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance()
            .register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance()
            .unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }
}
