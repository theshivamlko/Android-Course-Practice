package com.example.androidarchitecture.mvvm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityMvvmactivityBinding

class MVVMActivity : AppCompatActivity() {

    lateinit var activityMvvmactivityBinding: ActivityMvvmactivityBinding

    lateinit var adapter: ArrayAdapter<String>
    lateinit var viewModel: CountriesViewModel
    private var list = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("MVVM")

        activityMvvmactivityBinding=DataBindingUtil.setContentView(this,R.layout.activity_mvvmactivity)
        viewModel= ViewModelProvider(this).get(CountriesViewModel::class.java)

        activityMvvmactivityBinding.button5.setOnClickListener {

            onRetry()
        }
        adapter = ArrayAdapter(this, R.layout.item_list, R.id.textView, list)
        activityMvvmactivityBinding.listView.adapter = adapter


        observe()




    }

    fun observe(){
        viewModel.getCountries().observe(this){
            setValues(it)
        }
    }

    fun setValues(  newlist:List<String>){
        list.clear()
        list.addAll(newlist)

        adapter.notifyDataSetChanged()
    }

    fun onRetry(){

        viewModel.refresh()

    }


    companion object {
        fun startMVVM(context: Context) {

            context.startActivity(Intent(context, MVVMActivity::class.java))
        }
    }
}