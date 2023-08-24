package com.example.testingapp

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testingapp.playlisttest.idlingResource
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
abstract class BaseUnitTest {

    // run before execution test
    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun weAreDone(){
        IdlingRegistry.getInstance().unregister(idlingResource)
    }


    /*    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
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
        }*/

}