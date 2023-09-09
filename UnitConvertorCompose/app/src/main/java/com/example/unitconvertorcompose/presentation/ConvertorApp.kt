package com.example.unitconvertorcompose.presentation

//import io.flutter.embedding.android.FlutterActivity;

import android.app.Application
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor


//@HiltAndroidApp
class ConvertorApp:Application() {


     companion object{
          lateinit var flutterEngine: FlutterEngine
          const val FLUTTER_ENGINE_NAME="nps_flutter_engine_name"
     }

     override fun onCreate() {
          super.onCreate()

          flutterEngine= FlutterEngine(this)

          // execute Dart code
          flutterEngine.dartExecutor.executeDartEntrypoint(
               DartExecutor.DartEntrypoint.createDefault()
          )

          // cache flutter engine
          FlutterEngineCache.getInstance().put(FLUTTER_ENGINE_NAME,flutterEngine);

     }

}