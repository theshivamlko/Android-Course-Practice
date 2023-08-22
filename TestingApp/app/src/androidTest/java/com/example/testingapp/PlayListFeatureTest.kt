package com.example.testingapp

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.*
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.TypeSafeMatcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.Description
 import java.util.regex.Matcher


@RunWith(AndroidJUnit4::class)
class PlayListFeatureTest {

    val mainActivity=ActivityTestRule(MainActivity::class.java)
    @Rule get

    @Test
    fun displayScreenTitle() {

        assertDisplayed("Playlist")

    }

    @Test
    fun displayPlayList(){
        Thread.sleep(4000)


        assertRecyclerViewItemCount(R.id.playlist_list,50)


        onView(allOf(withId(R.id.playlist_name), isDescendantOfA(withId(R.id.playlist_list))))
            .check(matches(withText("Troy Hodkiewicz")))
            .check(matches(isDisplayed()))


        onView(allOf(withId(R.id.playlist_category), isDescendantOfA(withId(R.id.playlist_list))))
            .check(matches(withText("Fort Verniecester")))
            .check(matches(isDisplayed()))

        // Check Drawable
        onView(allOf(withId(R.id.imageView), isDescendantOfA(withId(R.id.playlist_list))))
            .check(matches(withDrawable(R.drawable.ic_launcher_background)))
            .check(matches(isDisplayed()))


    }





/*
    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }
*/

}

