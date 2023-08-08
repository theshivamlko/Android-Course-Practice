package com.example.unitconvertorapp

 import android.content.Intent
 import android.net.Uri
 import android.os.Bundle
 import android.os.PersistableBundle
 import android.view.ContextMenu
 import android.view.View
import android.widget.Button
import android.widget.EditText
 import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate")
        setContentView(R.layout.activity_main)

        val edt1=findViewById<EditText>(R.id.edt1)
        val edt2=findViewById<EditText>(R.id.edt2)
        val btn:Button=findViewById(R.id.btn)

          val btnIntent1: Button by lazy { findViewById<Button>(R.id.btnIntent1) }
        val btnIntent2: Button by lazy { findViewById(R.id.btnIntent2) }


        btn.setOnClickListener { it: View? ->
           val k=edt1.text.toString().toDouble()
            val res=poundLambda(k)
            edt2.setText("$res Pounds")
        }

        btnIntent1.setOnClickListener{
            var intent:Intent= Intent(this,ActivityIntent::class.java)
            intent.putExtra("num",123)
             startActivity(intent)

        }


        btnIntent2.setOnClickListener {
            var intent:Intent= Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://google.com"))
            startActivity(intent)
        }
    }

    var poundLambda= {kilo:Double -> kilo * 2.20462}

    fun convertToPound(kilo:Double):Double{
        var pound=kilo*2.20462
        return pound
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        println("onPostCreate")
    }

    override fun onStart() {
        super.onStart()
        println("onStart")

    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        println("onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        println("onDetachedFromWindow")
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        println("onCreateContextMenu")

    }

    override fun onPostResume() {
        super.onPostResume()
        println("onDetachedFromWindow")
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        println("onActivityReenter")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        println("onActivityReenter")
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
        println("onTopResumedActivityChanged")
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        println("onRestoreInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        println("onRestoreInstanceState persistentState")
    }

}

