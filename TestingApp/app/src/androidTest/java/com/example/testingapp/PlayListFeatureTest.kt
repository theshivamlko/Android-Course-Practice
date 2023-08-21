package com.example.testingapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class PlayListFeatureTest {

    val mainActivity=ActivityTestRule(MainActivity::class.java)
    @Rule get

    @Test
    fun displayScreenTitle() {

        assertDisplayed("Playlist")

    }
}