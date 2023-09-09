package com.example.unitconvertorcompose.utils

import android.content.Context
import com.example.unitconvertorcompose.data.roomdb.RoomDBService
import com.example.unitconvertorcompose.domain.repository.ConvertorRepositoryImpl
import com.example.unitconvertorcompose.presentation.ConvertorApp
import com.example.unitconvertorcompose.presentation.compose.convertor.ConvertorViewModelFactory
import io.flutter.embedding.android.KeyData.CHANNEL
import io.flutter.plugin.common.MethodChannel
import org.json.JSONObject

class CreateInstance {


    companion object {
        private val FLUTTER_CHANNEL = "com.example.flutter_module.host"
        fun createFactoryInstances(context: Context): ConvertorViewModelFactory {
            val db = RoomDBService.getInstance(context)
            val dao = db.convertorDAO()
            val repositoryImpl = ConvertorRepositoryImpl(dao)
            val factory = ConvertorViewModelFactory(repositoryImpl)

            return factory
        }

        fun openFlutterPage(counter: Int) {
            println("openFlutterPage ")
            val json = JSONObject()
            json.put("counter", counter.toString())
            println("openFlutterPage ${json.toString()}")

            val methodChannel = MethodChannel(
                ConvertorApp.flutterEngine.dartExecutor.binaryMessenger,
                FLUTTER_CHANNEL,
             )
             methodChannel.invokeMethod("getFromNative", json.toString())
        }
    }

}