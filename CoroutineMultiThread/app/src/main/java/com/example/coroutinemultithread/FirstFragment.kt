package com.example.coroutinemultithread

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.coroutinemultithread.databinding.FragmentFirstBinding
import java.util.concurrent.atomic.AtomicInteger

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

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

   @Volatile
    var count: Int = 0

    lateinit var atomiccount:AtomicInteger


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {

            count=0
            val thread = Thread(Runnable {
                println("Start")
                for (i in 1..10000) {
                    for (j in 1..100) {
                        count++

                    }

                }
                println(count)
            })


             val thread2 = Thread(Runnable {
                println("Start2")
                for (i in 1..10000) {
                    for (j in 1..100) {
                        count++

                    }

                }
                println(count)
            })
            thread.start()
            println("run")
            thread2.start()


        }

        binding.button2.setOnClickListener {

            atomiccount=AtomicInteger(0)
            val thread = Thread(Runnable {
                println("Start")
                for (i in 1..10000) {
                    for (j in 1..100) {
                        atomiccount.incrementAndGet()

                    }

                }
                println(atomiccount.get())
            })


            val thread2 = Thread(Runnable {
                println("Start2")
                for (i in 1..10000) {
                    for (j in 1..100) {
                        atomiccount.incrementAndGet()

                    }

                }
                println(atomiccount.get())
            })
            thread.start()
            println("run")
            thread2.start()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}