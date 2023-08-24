package com.example.androidarchitecture.mvc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityMvcactivityBinding
import com.example.androidarchitecture.model.Country
import okhttp3.internal.notify

class MVCActivity : AppCompatActivity() {

    var list = mutableListOf<String>( )
    lateinit var adapter: ArrayAdapter<String>
    lateinit var activityMvcactivityBinding: ActivityMvcactivityBinding
    lateinit var mvcController: CountryController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("MVC")

        mvcController=CountryController(this)

        activityMvcactivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_mvcactivity)
        adapter = ArrayAdapter(this, R.layout.item_list, R.id.textView, list)


        activityMvcactivityBinding.listview.adapter = adapter

        activityMvcactivityBinding.listview.setOnItemClickListener(){ a,view,index,long->
            println(list[index])
        }

        activityMvcactivityBinding.button4.setOnClickListener {
            onRetry()
        }




    }

    fun setValues(  newlist:List<String>){
        list.clear()
        list.addAll(newlist)

        adapter.notifyDataSetChanged()
    }

    fun onRetry(){
        mvcController.refresh()
    }

    companion object {
        fun startMVC(context: Context) {
            context.startActivity(Intent(context, MVCActivity::class.java))
        }
    }
}