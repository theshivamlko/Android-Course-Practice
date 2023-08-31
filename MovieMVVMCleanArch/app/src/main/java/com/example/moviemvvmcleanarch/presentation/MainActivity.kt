package com.example.moviemvvmcleanarch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.moviemvvmcleanarch.R
import com.example.moviemvvmcleanarch.databinding.ActivityMainBinding
import com.example.moviemvvmcleanarch.presentation.artist.ArtistFragment
import com.example.moviemvvmcleanarch.presentation.movie.MovieFragment
import com.example.moviemvvmcleanarch.presentation.tvshow.TvShowFragment

class MainActivity : AppCompatActivity() {


    lateinit var activityMainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        activityMainBinding.button.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()

            val movieFragment = MovieFragment().apply {
                arguments = Bundle().apply {
                    putString("name", "Shivam")
                    putParcelable("obj", User("ABCD", 123, 122.0, "H.No")!!)

                }
            }

            fragmentTransaction.replace(R.id.fragment, movieFragment).commit()
        }

        activityMainBinding.button2.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, TvShowFragment()).commit()
        }

        activityMainBinding.button3.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment, ArtistFragment()).commit()
        }


        //     (application as Injector).createMovieSubComponent().inject(this)
//        (application as Injector).createTVShowSubComponent().inject(this)
//        (application as Injector).createMovieSubComponent().inject(this)


        /* movieViewModel.getMovies().observe(this){
             println("MainActivity $it")
             println("MainActivity ${it.size}")
         }*/


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflator: MenuInflater = menuInflater
        inflator.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.refresh -> supportFragmentManager.fragments.forEach {
                if (it is MovieFragment) {
                    it.updateMovie()
                }
            }
        }

        return true
    }


}

data class User(
    val name: String,
    val age: Int,
    val salary: Double,
    val address: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(age)
        dest.writeDouble(salary)
        dest.writeString(address)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

