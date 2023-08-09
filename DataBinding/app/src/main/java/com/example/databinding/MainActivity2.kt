package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main2)

        var user=User("Shivam",32)
        var count=0

        binding.apply {
            button2.setOnClickListener {
                when(count){
                    0->{
                        println(count)
                        binding.model=user
                        count++
                    }
                    1-> {
                        println(count)
                        user.name = "ABDEF"
                        count++
                    }
                    2-> {
                         // INVALID , will not run
                        println(count)
                        binding.model?.name = "XXXXX"
                        binding.model?.run {
                            name="111111"
                        }
                        binding.model?.apply {
                            name="222222222"
                            notifyChange()
                        }
                        count++
                    }
                    3-> {
                        println(count)
                        var user=User("AAAAAAAA",32)
                        binding.model  =user
                        count++
                    }
                }


            }
        }


    }
}

data class User(
    var name: String,
    var age: Int

)