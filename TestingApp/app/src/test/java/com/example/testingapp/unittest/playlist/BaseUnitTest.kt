package com.example.testingapp.unittest.playlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testingapp.util.MainCoroutineScopeRule
import org.junit.Rule

open class BaseUnitTest {

    @get:Rule
    val coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

}