package com.example.coroutinemultithread

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.coroutinemultithread.databinding.FragmentFirstBinding
import kotlinx.coroutines.delay
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {

            var count: Int = 0

            count = 0

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
            val time = measureTimeMillis {
                thread.run()
            }
            println("END2 time ${time}")
            println("run")
            val time2 = measureTimeMillis {
                thread2.run()
            }
            println("END2 time2 ${time2}")


        }

        binding.button2.setOnClickListener {
            lateinit var atomiccount: AtomicInteger

            atomiccount = AtomicInteger(0)
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

        binding.button3.setOnClickListener {
            var LOCK = Any()


            var counter = 0
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


        binding.button4.setOnClickListener {
            var counter = 0
            val thread = Thread(Runnable {
                println("Start")
                for (i in 1..100) {
                    for (j in 1..100) {
                        println("Thread1== ${counter++}")
                    }
                }
                println("")
                println("END1 ${counter}")
            })

            val thread2 = Thread(Runnable {
                println("Start2")
                for (i in 1..100) {
                    for (j in 1..100) {
                        println("Thread2 ${counter++}")
                    }
                }
                println("END2 ${counter}")
            })
            thread.start()

            // wait some condition is meet else wait and sleep, then should exit
            while (true) {
                if (counter == 10000) {
                    println("PASSED")
                    Thread.sleep(2000)
                    break;
                } else if (counter > 10000) {
                    println("INFINITE")
                    break;
                }
            }
            thread2.start()
        }


        binding.button5.setOnClickListener {
            var counter = 0
            val thread = Thread(Runnable {
                println("Start")
                for (i in 1..100) {
                    for (j in 1..100) {
                        println("Thread1== ${counter++}")
                    }
                }
                println("")
                println("END1 ${counter}")
            })

            val thread2 = Thread(Runnable {
                println("Start2")
                for (i in 1..100) {
                    for (j in 1..100) {
                        println("Thread2 ${counter++}")
                    }
                }
                println("END2 ${counter}")
            })


            thread.start()
            thread.join() // wait thread to finish
            println("Main join ${thread.state}  ")


            thread2.start()
            println("Main returns  ")

        }


        binding.button6.setOnClickListener {

            /*
            var executor: ExecutorService = Executors.newCachedThreadPool(ThreadFactory {
                 println("ExecutorService ${it.toString()}")
                 Thread(it)
             })
             */


            //   var executor: ExecutorService = Executors.newFixedThreadPool(1)
            // Limits MAX no. of threads to use


            val executor: ThreadPoolExecutor =
                ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, ArrayBlockingQueue<Runnable>(10),
                    ThreadFactory {
                        println("ExecutorService ${it}")
                        Thread(it)
                    })



            var counter = 0


            repeat(5) {
                executor.execute {
                    val time = measureTimeMillis {
                        println("Start")
                        for (i in 1..10000) {
                            for (j in 1..100) {
                                counter++
                                //  println("Thread1== ${counter++}")
                            }
                        }
                    }
                    println("END1 ${counter} $time")
                }
            }


            executor.execute {
                println("Start2")
                val time = measureTimeMillis {

                    for (i in 1..10000) {
                        for (j in 1..100) {
                            counter++
                            //  println("Thread2 ${counter++}")
                        }
                    }
                }
                println("END2 ${counter} $time")
            }



            println("Main returns  ")

        }

        binding.button7.setOnClickListener {



            val executor: ThreadPoolExecutor =
                ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, ArrayBlockingQueue<Runnable>(10),
                    ThreadFactory {
                        println("ExecutorService ${it}")
                        Thread(it)
                    })



            var counter = 0


            repeat(5) {
                executor.execute {
                    val time = measureTimeMillis {
                        println("Start")
                        for (i in 1..10000) {
                            for (j in 1..100) {
                                counter++
                                //  println("Thread1== ${counter++}")
                            }
                        }
                    }
                    println("END1 ${counter} $time")
                }
            }


            executor.execute {
                println("Start2")
                val time = measureTimeMillis {

                    for (i in 1..10000) {
                        for (j in 1..100) {
                            counter++
                            //  println("Thread2 ${counter++}")
                        }
                    }
                }
                println("END2 ${counter} $time")
            }



            println("Main returns  ")

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}