package com.example.unitconvertorapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate")
        setContentView(R.layout.activity_main)

        val edt1 = findViewById<EditText>(R.id.edt1)
        val edt2 = findViewById<EditText>(R.id.edt2)
        val btn: Button = findViewById(R.id.btn)

        val btnIntent1 = lazy { findViewById<Button>(R.id.btnIntent1) }.value
        val btnIntent2: Button by lazy { findViewById(R.id.btnIntent2) }
        val btnIntent3: Button by lazy { findViewById(R.id.btnIntent3) }
        val btn4: Button by lazy { findViewById(R.id.btn4) }


        btn.setOnClickListener { it: View? ->
            val k = edt1.text.toString().toDouble()
            val res = poundLambda(k)
            edt2.setText("$res Pounds")
            btn.setBackgroundColor(getColor(R.color.purple_200))
            btn.setTextColor(android.graphics.Color.parseColor("#A89700"))
        }

        btnIntent1.setOnClickListener {
            var intent: Intent = Intent(this, ActivityIntent::class.java)
            intent.putExtra("num", 123)
            startActivity(intent)
            btnIntent1.setBackgroundColor(
                resources.getColor(
                    R.color.purple_700,
                    resources.newTheme().apply { R.style.Theme_UnitConvertorApp }))
            btnIntent1.setTextColor(android.graphics.Color.GREEN)

        }


        btnIntent2.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://google.com"))
            startActivity(intent)
            println("btnIntent2")
            btnIntent2.setTextColor(ContextCompat.getColor(this, R.color.black))

            /*  var intent:Intent= Intent(Intent.ACTION_SEND)
              intent.putExtra(Intent.EXTRA_SUBJECT,"Hi everyone" )
              intent.putExtra(Intent.EXTRA_TEXT,"Some random description" )
              intent.setType("text/plain")
              startActivity(intent)*/
        }


        btnIntent3.setOnClickListener {
            btnIntent3.setText(resources.getText(R.string.sttringex))
            resources.getTextArray(R.array.strarray).forEach { println(it) }
            resources.getStringArray(R.array.strarray).forEach { println(it) }
        }

        btn4.setOnClickListener {
            var intent: Intent = Intent(this, DrawableActivity::class.java)
            startActivity(intent)
        }
    }

    var poundLambda = { kilo: Double -> kilo * 2.20462 }

    fun convertToPound(kilo: Double): Double {
        var pound = kilo * 2.20462
        return pound
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        println("onCreateOptionsMenu")
        val menu1: MenuInflater = menuInflater;
        menu1.inflate(R.menu.main_menu, menu)
        return true;
    }

    var counter:Int=0
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println("onOptionsItemSelected")

        when (item.itemId) {
            R.id.menu1 -> println(item.title)
            R.id.menu2 -> println(item.title)
            R.id.menu3 -> println(item.title)
            R.id.menu4 -> {
                viewAlertCountTextview.setText("${++counter}")
            }
        }
        return false
    }
    lateinit var viewAlertCountTextview:TextView
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        println("onPrepareOptionsMenu")
        val alertMenuItem = menu?.findItem(R.id.menu4)
        val rootView: FrameLayout =   alertMenuItem?.actionView as FrameLayout
        println("onPrepareOptionsMenu $rootView")
          val viewAlertRedCircle: FrameLayout by lazy { rootView.findViewById<FrameLayout>(R.id.view_alert_red_circle) }
            viewAlertCountTextview=  lazy { rootView.findViewById<TextView>(R.id.view_alert_count_textview) }.value

        rootView.setOnClickListener{
            onOptionsItemSelected(alertMenuItem);
        }
        return super.onPrepareOptionsMenu(menu);
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

