package com.example.unitconvertorapp

 import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
 import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edt1=findViewById<EditText>(R.id.edt1)
        val edt2=findViewById<EditText>(R.id.edt2)
        val btn:Button=findViewById(R.id.btn)


        btn.setOnClickListener { it: View? ->
           val k=edt1.text.toString().toDouble()
            val res=poundLmbda(k)
            edt2.setText("$res Pounds")
        }
    }

    var poundLmbda= {kilo:Double -> kilo*2.20462}

    fun convertToPound(kilo:Double):Double{
        var pound=kilo*2.20462
        return pound
    }
}