package com.example.networkdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.networkdemo.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val retrofitInstance = RetrofitInstance.getInstance().create(AlbumService::class.java)

        val reponseLiveData: LiveData<Response<AlbumsList>> = liveData {
            val response = retrofitInstance.getAllAlbums()

            emit(response)
        }

        var albumsList2: List<AlbumItem>

        reponseLiveData.observe(this) {

            println("status ${it.code()}")
            val albumsList = it.body()?.listIterator()
            albumsList2 = it.body()?.toList() ?: listOf()

            if (albumsList != null) {

                while (albumsList.hasNext()) {
                    val album = albumsList.next()

                    Log.e("MainActivity", "Album Name ${album.albumName}")
                }
            }

            val arrayAdapter: ArrayAdapter<AlbumItem> =
                object : ArrayAdapter<AlbumItem>(this, android.R.layout.simple_list_item_1) {

                    override fun getCount(): Int {
                        return albumsList2.size
                    }

                    override fun getView(
                        position: Int,
                        convertView: View?,
                        parent: ViewGroup
                    ): View {

                        val view: View =
                            View.inflate(context, android.R.layout.simple_list_item_1, null)
                        val textView = view as TextView
                        textView.setText("${albumsList2[position].userId}\n${albumsList2[position].albumID}\n${albumsList2[position].albumName}")
                        textView.setPadding(20)

                        return view
                    }

                }


            activityMainBinding.listView.adapter = arrayAdapter



            var resposneLiveData: LiveData<Response<AlbumsList>>
            activityMainBinding.listView.setOnItemClickListener { a, v, i, l ->

                resposneLiveData = liveData {

                    val response = retrofitInstance.getAlbum(albumsList2[i].albumID)
                    emit(response)
                }

                resposneLiveData.observe(this@MainActivity) {

                    println("Resposne ${it.body()}")
                    val album=it.body()?.get(0)
                    println("Resposne ${album?.albumName}")
                   val alert= AlertDialog.Builder(this@MainActivity)
                        .setTitle(album?.albumName)
                        .setMessage(album?.albumName)
                        .setPositiveButton("OK") { dialog, v ->

                            dialog.dismiss()

                        }

                    alert.show()
                }
            }

        }

    }
}