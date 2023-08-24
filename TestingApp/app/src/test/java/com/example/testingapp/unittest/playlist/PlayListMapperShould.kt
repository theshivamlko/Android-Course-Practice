package com.example.testingapp.unittest.playlist

import com.example.testingapp.playlisttest.PlayListMapper
import org.junit.Test
import org.mockito.kotlin.mock

class PlayListMapperShould:BaseUnitTest() {


    val platListMapper:PlayListMapper= mock()

    @Test
    fun keepSameId(){

        println(platListMapper)
        println(platListMapper.invoke(listOf()))
      //  assertEquals(platListRaw.id,playlist.id)
    }

}