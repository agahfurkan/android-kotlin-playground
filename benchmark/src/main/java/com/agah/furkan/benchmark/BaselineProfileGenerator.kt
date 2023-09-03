package com.agah.furkan.benchmark

import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@OptIn(ExperimentalBaselineProfilesApi::class)
class BaselineProfileGenerator {
    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @OptIn(ExperimentalBaselineProfilesApi::class)
    @Test
    fun generate() = baselineProfileRule.collectBaselineProfile(
        packageName = "com.agah.furkan.androidplayground.dev",
        profileBlock = {
            pressHome()
            startActivityAndWait()
        }
    )
}