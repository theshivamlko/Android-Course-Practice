package com.example.androidarchitecture.mvp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.androidarchitecture.R
import com.example.androidarchitecture.databinding.ActivityMvpactivityBinding
import com.example.androidarchitecture.model.Country

class MVPActivity : AppCompatActivity() ,IAppView{

    lateinit var activityMvpactivityBinding: ActivityMvpactivityBinding

    private var list = mutableListOf<String>()
    lateinit var adapter: ArrayAdapter<String>
    lateinit var countryPresenter: CountriesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle("MVP")


        activityMvpactivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_mvpactivity)

        countryPresenter= CountriesPresenter(this)

        activityMvpactivityBinding.button5.setOnClickListener {

            refresh()

        }

        adapter = ArrayAdapter(this, R.layout.item_list, R.id.textView, list)


        activityMvpactivityBinding.listView.adapter = adapter

        activityMvpactivityBinding.listView.setOnItemClickListener(){ a,view,index,long->
            println(list[index])
        }


    }


    companion object {
        fun startMVP(context: Context) {
            context.startActivity(Intent(context, MVPActivity::class.java))
        }
    }

    override fun setValues(newList: List<String>) {
        list.clear()
        list.addAll(newList)

        adapter.notifyDataSetChanged()
    }

    override fun onError() {
        TODO("Not yet implemented")
    }

    override fun refresh() {
        countryPresenter.refresh()

    }

}