package com.example.noteapproommvvm

import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapproommvvm.databinding.FragmentListBinding
import com.example.noteapproommvvm.livedata.NoteViewModel
import com.example.noteapproommvvm.roomdb.NoteEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() ,SearchView.OnQueryTextListener{

  lateinit     var fragmeListBinding :FragmentListBinding
  lateinit     var noteViewModel: NoteViewModel
  lateinit     var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           // param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmeListBinding=FragmentListBinding.inflate(inflater, container,false)


        return fragmeListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel=(activity as MainActivity).noteViewModel
        fragmeListBinding.fabBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_listFragment_to_addNoteFragment)
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.search -> {
                       println("SearchMenu")
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        setRecycleView()
    }

    fun setRecycleView(){

        myAdapter= MyAdapter()


        fragmeListBinding.recyclerView.apply {
            layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

            setHasFixedSize(true)
            adapter=myAdapter
        }

        activity?.let {
            noteViewModel.getAllNote().observe(viewLifecycleOwner){
                myAdapter.differ.submitList(it)

                updateUI(it)
            }
        }


    }

    fun updateUI(notesList:  List<NoteEntity> ){
        if(notesList.isEmpty()){



        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {

       // searchNote(p0)
        return  false
    }

    override fun onQueryTextChange(p0: String?): Boolean {

        if(p0!=null && p0.length>3){
            searchNote(p0)
        }

        return  true
     }


    fun searchNote(str:String){

        noteViewModel.searchNote("%$str%").observe(this){
            myAdapter.differ.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmeListBinding.unbind()
    }


}