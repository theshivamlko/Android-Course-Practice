package com.example.noteapproommvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.noteapproommvvm.databinding.FragmentAddNoteBinding
import com.example.noteapproommvvm.databinding.FragmentListBinding
import com.example.noteapproommvvm.livedata.NoteViewModel
import com.example.noteapproommvvm.roomdb.NoteEntity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNoteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var fragmentAddNoteBinding: FragmentAddNoteBinding
    lateinit var noteViewModel: NoteViewModel
    lateinit var mview: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAddNoteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)

        return fragmentAddNoteBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        mview = view

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_newnote, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.save -> {
                        println("SAVE Menu")
                        true
                    }

                    else -> false
                }
            }
        })
    }


    fun saveNote() {
        val title = fragmentAddNoteBinding.editTextText.text.toString()
        val description = fragmentAddNoteBinding.editTextTextMultiLine.text.toString()

        if (title.isNotEmpty() && description.isNotEmpty()) {
            val note = NoteEntity(title = title, description = description)

            noteViewModel.addNote(note)
            println("NOTE ADDED")

            mview.findNavController().navigate(R.id.action_addNoteFragment_to_listFragment)
        }
    }


}