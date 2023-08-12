package com.example.kcoroutines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kcoroutines.databinding.FragmentSecondBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    val TAG="SecondFragment"

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
           // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)


            // Execute in InOrder
       /*     CoroutineScope(Dispatchers.IO).launch {
                println("TAG  CoroutineScope doSomething START")
                val one=doSomething()
                println("TAG  CoroutineScope doSomething2 START")
                val two=doSomething2()  // waits for doSomething to finish, in order
                val result = one+two
                println("TAG  CoroutineScope one $one")
                println("TAG  CoroutineScope two $two")
            }
        }
        */

        // In Parallel
            CoroutineScope(Dispatchers.IO).launch {
                println("TAG  CoroutineScope doSomething START")
                val one= async { doSomething() }
                println("TAG  CoroutineScope doSomething2 START")
                val two=async { doSomething2() }

                val result = one.await() + two.await()  // will take total 7 sec bcoz parallel
                println("TAG  CoroutineScope result $result")
                println("TAG  CoroutineScope one $one")
                println("TAG  CoroutineScope two $two")
            }
        }
    }


    suspend fun doSomething():Int{
        delay(5000)
        println("TAG  doSomething")
        return 11
    }

    suspend fun doSomething2():Int{
        delay(7000)
        println("TAG  doSomething2")
        return 22
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}