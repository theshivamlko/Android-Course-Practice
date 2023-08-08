package com.example.unitconvertorapp

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class ActivityIntent : Activity() {

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_main2)
        val textView2 = lazy { findViewById<TextView>(R.id.textView2) }.value

        val bundle = intent.extras
        val num = bundle?.getInt("num")
        textView2.setText("$num")

    }

}