package com.example.testingapp.playlisttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.testingapp.R
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class PlayListFragment : Fragment() {

    lateinit var playListViewModel: PlayListViewModel

    @Inject
    lateinit var playListViewModelFactory: PlayListViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_play_list, container, false)

        setUpList()
        playListViewModel.playList.observe(this as LifecycleOwner) {

            if (it.getOrNull() != null) {
                // Set the adapter
                if (view is RecyclerView) {

                    setUpView(view)
                }
            }


        }


        return view
    }

    private fun setUpView(view: RecyclerView) {
        view.layoutManager = LinearLayoutManager(context)

        println("setUpView ${playListViewModel.playList.value?.getOrNull()?.size}")
        view.adapter =
            MyPlayListRecyclerViewAdapter(playListViewModel.playList.value?.getOrNull()!!)

    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            PlayListFragment().apply {
                arguments = Bundle()
            }
    }

    fun setUpList() {
      //  playListViewModelFactory = PlayListViewModelFactory(playListRepository) done by Hilt
        playListViewModel =
            ViewModelProvider(this, playListViewModelFactory).get(PlayListViewModel::class.java)


    }
}