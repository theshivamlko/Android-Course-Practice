package com.example.coroutinemultithread

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.coroutinemultithread.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
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

            })


             val thread2 = Thread(Runnable {
                println("Start2")
                for (i in 1..10000) {
                    for (j in 1..100) {
                        count++
                    }
                }
                 println("END2 ${count}")
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
        var LOCK=Any()

        binding.button3.setOnClickListener {

            var counter=0
            val thread = Thread(Runnable {
                println("Start")
                for (i in 1..10000) {
                    for (j in 1..100) {
                        Thread.sleep(1000)
                        synchronized(LOCK) {
                            println("Thread1== ${counter++}")
                        }

                    }

                }
                println("")
                println("END1 ${counter}")
            })


            val thread2 = Thread(Runnable {
                println("Start2")
                for (i in 1..10000) {
                    for (j in 1..100) {
                        Thread.sleep(1000)
                        synchronized(LOCK) {
                            println("Thread2 ${counter++}")
                        }

                    }

                }
                println("END2 ${counter}")
            })
            thread.start()

            thread2.start()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}