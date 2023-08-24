package com.example.testingapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.junit.Test

class NewPageTest:BaseUnitTest() {

    @Test
    fun displayPlayListNameDetails(){

        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.playlist_list),
                ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.playlist_list))),
            )
        ).perform(ViewActions.click())

        assertDisplayed("Hard")
        assertDisplayed("Rock your")
    }
}