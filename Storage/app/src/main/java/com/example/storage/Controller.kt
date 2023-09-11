package com.example.storage

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.UriPermission
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import java.io.File
import java.io.FileFilter
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.URI
import java.text.DateFormat
import java.util.Date


class Controller {

    companion object {
        var readPermissionGranted = false
        var writePermissionGranted = false

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

        fun saveImageToExternalStorage(context: Context, filename: String, bmp: Bitmap): Boolean {
            var list: Uri?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                list = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            } else {
                list = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }


            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "$filename.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(
                    MediaStore.Images.Media.DATE_MODIFIED,
                    DateFormat.getInstance().format(Date(System.currentTimeMillis()))
                )
            }

            // will save meta data only
            context.contentResolver.insert(list, contentValues).apply {

                this?.let {
                    context.contentResolver.openOutputStream(it).apply {

                        bmp.compress(Bitmap.CompressFormat.JPEG, 80, this)
                    }
                }
            }


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

        fun writeFileExternalStorage(context: Context, filename: String, bytes: ByteArray) {

            val file = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS
            )

            val textFile =
                File(file.path + File.separator + "Shivam" + File.separator + "$filename.jpg")
            println("writeFileExternalStorage ${textFile.path}")
            println("writeFileExternalStorage ${bytes.size}")
            textFile.parentFile.mkdirs()
            textFile.createNewFile()
            textFile.writeBytes(bytes)

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

        fun requestPermission(
            context: Context,
            //  permission: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>
        ) {

            // will be false on 29 Q and above
            var hasPermission =
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            println("requestPermission $hasPermission")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (!hasPermission) {
                    println("requestPermission $hasPermission")


                    /*  ActivityCompat.requestPermissions(
                          context as Activity,
                          arrayOf<String>(
                              Manifest.permission.WRITE_EXTERNAL_STORAGE
                          ), 1
                      )*/

                    /*  (context as ComponentActivity).requestPermissions(
                          arrayOf<String>(
                              Manifest.permission.WRITE_EXTERNAL_STORAGE
                          ), 1
                      )*/
                }
            }
        }

        fun updateFile(context: Context, uri: Uri) {
            println("updateFile3 ${uri}")

            // var file= uri.toFile()
            //   println("updateFile4 ${file.exists()}")
//            context.contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            //   context.contentResolver.acquireContentProviderClient(uri )

            /* var name = ""
             val returnCursor = context.contentResolver.query(uri, null, null, null, null)
             if (returnCursor != null) {
                 val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                 returnCursor.moveToFirst()
                 name = returnCursor.getString(nameIndex)
                 returnCursor.close()
             }
             println("updateFile4 ${name}")*/

            /*   val takeFlags: Int = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    //   or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
               uri?.let {
                   context.contentResolver.takePersistableUriPermission(it, takeFlags)
               }*/


            val fileDescriptor =
                context.contentResolver.openOutputStream(uri)
            var fileOutputStream = OutputStreamWriter(
                fileDescriptor
            )
            fileOutputStream.appendLine("Append this text")
            fileOutputStream.close()
            fileDescriptor?.close()
        }
    }


}