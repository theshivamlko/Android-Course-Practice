package com.example.moviemvvmcleanarch.presentation.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviemvvmcleanarch.BuildConfig
import com.example.moviemvvmcleanarch.R
import com.example.moviemvvmcleanarch.data.api.TMDBService
import com.example.moviemvvmcleanarch.data.repository.movie.MovieRepositoryImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieCacheDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieLocalDBDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasource.IMovieRemoteDataSource
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieLocalDBDataSourceImpl
import com.example.moviemvvmcleanarch.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.moviemvvmcleanarch.data.roomdb.TMDBRoomDB
import com.example.moviemvvmcleanarch.databinding.ActivityMainBinding
import com.example.moviemvvmcleanarch.databinding.FragmentMovieBinding
import com.example.moviemvvmcleanarch.domain.repository.IMovieRepository
import com.example.moviemvvmcleanarch.domain.usecase.GetMoviesUseCase
import com.example.moviemvvmcleanarch.domain.usecase.UpdateMoviesUseCase
import com.example.moviemvvmcleanarch.presentation.User
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "name"
private const val ARG_PARAM2 = "obj"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: User? = null

    lateinit var movieViewModelFactory: MovieViewModelFactory


    lateinit var movieViewModel: MovieViewModel


    lateinit var fragmentMovieBinding: FragmentMovieBinding

    lateinit var roomDatabase: TMDBRoomDB
    lateinit var localContext: Context
    lateinit var adapter: MovieAdapter


    override fun onAttach(c: Context) {
        super.onAttach(c)
        this.localContext = c
        roomDatabase = Room.databaseBuilder(c, TMDBRoomDB::class.java, "mydb")
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate $arguments")

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getParcelable<User> ("obj",User::class.java)
            println("onCreate")
            println(param2?.name)
            println(param2?.age)
            println(param2?.address)
            println(param2?.salary)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        fragmentMovieBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        initMovie()

        return fragmentMovieBinding.root
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
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
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


        val iMovieRemoteDataSource: IMovieRemoteDataSource = MovieRemoteDataSourceImpl(tmdbService)
        val iMovieLocalDBDataSource: IMovieLocalDBDataSource =
            MovieLocalDBDataSourceImpl(roomDatabase.movieDAO())
        val iMovieCacheDataSource: IMovieCacheDataSource = MovieCacheDataSourceImpl()


        val movieRepository: IMovieRepository = MovieRepositoryImpl(
            iMovieRemoteDataSource,
            iMovieLocalDBDataSource,
            iMovieCacheDataSource
        )
        val getMoviesUseCase = GetMoviesUseCase(movieRepository)
        val updateMoviesUseCase = UpdateMoviesUseCase(movieRepository)

        movieViewModelFactory = MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)


        movieViewModel = ViewModelProvider(this, movieViewModelFactory)
            .get(MovieViewModel::class.java)

        initRecyclerView()
    }

    fun initRecyclerView() {
        fragmentMovieBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MovieAdapter()

        fragmentMovieBinding.recyclerView.adapter = adapter

        showMovieList()

    }

    fun showMovieList() {
        fragmentMovieBinding.progressBar.visibility = View.VISIBLE

        movieViewModel.getMovies().observe(viewLifecycleOwner) {
            println("MainActivity getMovies $it")
            if (it != null) {
                adapter.refreshList(it)
                adapter.notifyDataSetChanged()
                fragmentMovieBinding.progressBar.visibility = View.GONE
            } else {
                fragmentMovieBinding.progressBar.visibility = View.GONE
                Toast.makeText(
                    context, "No data available",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    fun updateMovie(){
        fragmentMovieBinding.progressBar.visibility=View.VISIBLE
        fragmentMovieBinding.recyclerView.visibility=View.GONE

        movieViewModel.updateMovies().observe(viewLifecycleOwner){
            Thread.sleep(1000)
            if (it != null) {
                adapter.refreshList(it)
                adapter.notifyDataSetChanged()
                fragmentMovieBinding.progressBar.visibility = View.GONE
                fragmentMovieBinding.recyclerView.visibility=View.VISIBLE
            } else {
                fragmentMovieBinding.progressBar.visibility = View.GONE
                Toast.makeText(
                    context, "No data available",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }


}