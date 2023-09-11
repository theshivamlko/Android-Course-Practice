package com.example.storage

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import java.io.FileFilter
import java.io.FileOutputStream
import java.io.IOException

class Controller {

    companion object {
        fun saveFileToInternalStorage(context: Context, filename: String, bmp: Bitmap): Boolean {

            lateinit var fileOutputStream: FileOutputStream
            println("saveFileToInternalStorage $filename")

            try {
                fileOutputStream = context.openFileOutput("$filename.jpg", 0)
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream)) {
                    throw IOException("Compress Failed")
                }

                return true
            } catch (e: Exception) {
                println("saveFileToInternalStorage $e")
            } finally {
                fileOutputStream.close()
            }

            return false
        }

        fun loadPhotoFromInternalStorage(context: Context): List<AppFile> {
            println("loadPhotoFromInternalStorage")
            try {

                var files = context.filesDir.listFiles(FileFilter {
                    it.canRead() && it.name.contains(".jpg")
                });

                val list = files.map {
                    val bytes = it.readBytes();
                    val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    AppFile(it.name, bmp)
                }.toList()

                return list
            } catch (e: Exception) {
                println("loadPhotoFromInternalStorage $e")
            }

            return emptyList()
        }

        fun deletePhotoFromInternalStorage(file: String, context: Context): Boolean {

            try {
                return context.deleteFile(file)
            } catch (e: Exception) {
                println("deletePhotoFromInternalStorage $e")

            }

            return false
        }
    }
}