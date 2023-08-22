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

/**
 * A fragment representing a list of Items.
 */
class PlayListFragment : Fragment() {
    lateinit var playListViewModel: PlayListViewModel
    lateinit var playListViewModelFactory: PlayListViewModelFactory

    var playListAPI = PlayListAPI(object :API{
        override fun fetchAllPlayList(): List<PlayList> {
            return super.fetchAllPlayList()
        }
    })

    var playListRepository = PlayListRepository(playListAPI)

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
        playListViewModelFactory = PlayListViewModelFactory(playListRepository)
        playListViewModel =
            ViewModelProvider(this, playListViewModelFactory).get(PlayListViewModel::class.java)

    }
}