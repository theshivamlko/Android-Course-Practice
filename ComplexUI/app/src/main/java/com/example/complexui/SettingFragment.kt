package com.example.complexui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {


    lateinit var    activity:MainActivity;


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity=context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view= inflater.inflate(R.layout.fragment_setting, container, false)

      val viewPager2:ViewPager2=  view.findViewById<ViewPager2>(R.id.viewpager)

        val viewPageAdapter:ViewPageAdapter= ViewPageAdapter(activity )

        viewPager2.orientation=ViewPager2.ORIENTATION_HORIZONTAL
        viewPager2.adapter=viewPageAdapter

        return view
    }


}