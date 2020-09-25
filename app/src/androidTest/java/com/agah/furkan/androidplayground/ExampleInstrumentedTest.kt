package com.agah.furkan.androidplayground

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agah.furkan.androidplayground.ui.MainActivity
import com.agah.furkan.androidplayground.ui.detail.PokemonAbilityListAdapter
import com.agah.furkan.androidplayground.util.CountingIdlingResourceSingleton
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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

    @Test
    fun clickPokemonElement_NavigateDetailScreen() {
        onView(withId(R.id.main_screen_pokemon_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.main_screen_pokemon_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PokemonAbilityListAdapter.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.pokemon_id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
