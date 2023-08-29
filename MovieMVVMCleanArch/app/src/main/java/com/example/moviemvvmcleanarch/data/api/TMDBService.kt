package com.example.moviemvvmcleanarch.data.api

 import androidx.lifecycle.LiveData
import com.example.moviemvvmcleanarch.data.model.ArtistsList
import com.example.moviemvvmcleanarch.data.model.MovieList
import com.example.moviemvvmcleanarch.data.model.TVShowsList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface TMDBService {

    @Headers("""{
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGE3N2VjNGVmNTc1MjQ5M2JjOTcwNzhlNGNhMjM2OCIsInN1YiI6IjViMTZjNjVlYzNhMzY4NTM1MjAxYjdlMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AIeI1DR3xQwveg6KeEKfaoclVxEtK0AXHU14HOeyu3A",
        "accept: application/json"
    }""")
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieList>

    @Headers("""{
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGE3N2VjNGVmNTc1MjQ5M2JjOTcwNzhlNGNhMjM2OCIsInN1YiI6IjViMTZjNjVlYzNhMzY4NTM1MjAxYjdlMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AIeI1DR3xQwveg6KeEKfaoclVxEtK0AXHU14HOeyu3A",
        "accept: application/json"
    }""")
    @GET("tv/popular")
    suspend fun getPopularTVShows(): Flow<TVShowsList>


    @Headers("""{
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGE3N2VjNGVmNTc1MjQ5M2JjOTcwNzhlNGNhMjM2OCIsInN1YiI6IjViMTZjNjVlYzNhMzY4NTM1MjAxYjdlMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AIeI1DR3xQwveg6KeEKfaoclVxEtK0AXHU14HOeyu3A",
        "accept: application/json"
    }""")
    @GET("person/popular")
    suspend fun getPopularArtists(): LiveData<ArtistsList>



}