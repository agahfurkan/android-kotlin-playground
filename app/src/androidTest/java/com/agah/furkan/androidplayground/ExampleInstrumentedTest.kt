package com.agah.furkan.androidplayground

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agah.furkan.androidplayground.data.local.model.PokemonCache
import com.agah.furkan.androidplayground.data.repository.PokemonRepository
import com.agah.furkan.androidplayground.ui.MainActivity
import com.agah.furkan.androidplayground.ui.detail.PokemonAbilityListAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var mActivityTestRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Mock
    val rep = mock(PokemonRepository::class.java)

   /* @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()*/

    @Mock
    var observer: Observer<List<PokemonCache>>? = null

    @Before
    fun init() {
        /*val viewmodel = mock(MainScreenVM::class.java)
        viewmodel.getPokemonDataFromNetwork(0)*/
    }

    @Test
    fun clickPokemonElement_NavigateDetailScreen() {
        onView(withId(R.id.main_screen_pokemon_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
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