package com.example.moviemvvmcleanarch.presentation.tvshow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.moviemvvmcleanarch.BuildConfig
import com.example.moviemvvmcleanarch.R
import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.repository.movie.MovieRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.TVShowRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.ITVShowRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.TVShowRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.roomdb.TMDBRoomDB
import com.example.moviemvvmcleanarch.databinding.FragmentMovieBinding
import com.example.moviemvvmcleanarch.databinding.FragmentTvShowBinding
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import com.example.moviemvvmcleanarch.domain.repository.ITVShowsRepository
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.GetTVShowUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateTVShowUseCase

import com.example.moviemvvmcleanarch.presentation.movie.MovieAdapter
import com.example.moviemvvmcleanarch.presentation.movie.MovieFragment
import com.example.moviemvvmcleanarch.presentation.movie.MovieViewModel
import com.example.moviemvvmcleanarch.presentation.movie.MovieViewModelFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [TvShowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TvShowFragment : Fragment() {
    // TODO: Rename and change types of parameters
 

    lateinit var tvShowViewModelFactory: TvShowViewModelFactory


    lateinit var tvShowViewModel: TvShowViewModel


    lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    lateinit var roomDatabase: TMDBRoomDB
    lateinit var localContext: Context
    lateinit var adapter: TVShowAdapter


    override fun onAttach(c: Context) {
        super.onAttach(c)
        this.localContext = c
        roomDatabase = Room.databaseBuilder(c, TMDBRoomDB::class.java, "mydb")
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {


        fragmentTvShowBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        initMovie()

        return fragmentTvShowBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                   
                }
            }
    }


    fun initMovie() {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {

            addInterceptor(interceptor)
            // time period in which app should establish connection,
            // after 30 sec it will stop trying, default 10 sec
            connectTimeout(2, TimeUnit.SECONDS)
            // max timeout b/w arrivals of 2 data packets in waiting for response
            readTimeout(5, TimeUnit.SECONDS)
            // Time gap b/w 2 data packets when sending them 2 server
            writeTimeout(5, TimeUnit.SECONDS)

            interceptors().add(Interceptor { chain ->


                var request: Request = chain.request()
                request = request.newBuilder()
                    .build()
                val response = chain.proceed(request)
                println("Retry response ${response.code}")
                when (response.code) {
                    400 -> {
                        //Show Bad Request Error Message
                    }

                    401 -> {
                        //Show UnauthorizedError Message
                    }

                    403 -> {
                        //Show Forbidden Message
                    }

                    404 -> {
                        //Show NotFound Message
                    }
                    // ... and so on
                }
                return@Interceptor response
            })
        }.build()

        val tmdbService: TMDBService = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build().create(TMDBService::class.java)


        val iTvshowRemoteDataSource:ITVShowRemoteDataSource = TVShowRemoteDataSourceImpl(tmdbService)
        val iTvLocalDBDataSource:ITVShowLocalDBDataSource =
            TVShowLocalDBDataSourceImpl(roomDatabase.tvShowDAO())
        val iTVCacheDataSource: ITVShowCacheDataSource = TVShowCacheDataSourceImpl()


        val itvShowsRepository: ITVShowsRepository= TVShowRepositoryImpl(
            iTvshowRemoteDataSource,
            iTvLocalDBDataSource,
            iTVCacheDataSource
        )
        val getMoviesUseCase = GetTVShowUseCase(itvShowsRepository)
        val updateMoviesUseCase = UpdateTVShowUseCase(itvShowsRepository)

        tvShowViewModelFactory = TvShowViewModelFactory(getMoviesUseCase, updateMoviesUseCase)


        tvShowViewModel = ViewModelProvider(this, tvShowViewModelFactory)
            .get(TvShowViewModel::class.java)

        initRecyclerView()
    }

    fun initRecyclerView() {
        fragmentTvShowBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TVShowAdapter()

        fragmentTvShowBinding.recyclerView.adapter = adapter

        showMovieList()

    }

    fun showMovieList() {
        fragmentTvShowBinding.progressBar.visibility = View.VISIBLE

        tvShowViewModel.getTvShows().observe(viewLifecycleOwner) {
            println("TvShowFragment getTvShows $it")
            if (it != null) {
                adapter.refreshList(it)
                adapter.notifyDataSetChanged()
                fragmentTvShowBinding.progressBar.visibility = View.GONE
            } else {
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                Toast.makeText(
                    context, "No data available",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    fun updateMovie(){
        fragmentTvShowBinding.progressBar.visibility=View.VISIBLE
        fragmentTvShowBinding.recyclerView.visibility=View.GONE

        tvShowViewModel.updateTVShows().observe(viewLifecycleOwner){
            Thread.sleep(1000)
            if (it != null) {
                adapter.refreshList(it)
                adapter.notifyDataSetChanged()
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                fragmentTvShowBinding.recyclerView.visibility=View.VISIBLE
            } else {
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                Toast.makeText(
                    context, "No data available",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }


}  