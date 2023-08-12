package com.example.kcoroutines

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kcoroutines.databinding.FragmentFirstBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val TAG="FirstFragment"

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var counter=1;

        binding.buttonFirst.setOnClickListener {

            CoroutineScope(Dispatchers.Default).launch {
                downloadFile()

            }
         //   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonFirst2.setOnClickListener {
            _binding?.textView1?.text="${++counter}"
         //   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonFirst3.setOnClickListener {

           findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

  suspend fun    downloadFile(){
        for (i in 1..10000000){
            var num1= (10..99).random()
            var num2= (10..99).random()
           Log.e(TAG,"downloadFile ${Thread.currentThread().name}  ${Thread.currentThread().id} $i  $num1$num2$num1")
            _binding?.textView2?.text="${ i}"
            _binding?.textView2?.setTextColor(Color.parseColor("#$num1$num2$num1"))

              num1= (10..99).random()
              num2= (10..99).random()
            withContext(Dispatchers.Main){
                _binding?.textView2?.setBackgroundColor(Color.parseColor("#$num1$num2$num1"))

            }

        }
    }
}