package com.example.storage

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storage.ui.theme.StorageTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.example.storage.Controller.Companion.deletePhotoFromInternalStorage
import com.example.storage.Controller.Companion.loadPhotoFromInternalStorage
import com.example.storage.Controller.Companion.requestPermission
import kotlinx.coroutines.launch

lateinit var cameraPermissionLauncher: ActivityResultLauncher<String>

lateinit var galleryLauncher: ManagedActivityResultLauncher<String, List<@JvmSuppressWildcards Uri>>

class MainActivityExternalStorage : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        loadPhotoFromInternalStorage(baseContext)


        setContent {
            StorageTheme {


                cameraPermissionLauncher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
                        println("cameraPermissionLauncher $isGranted")
                    }


                // requestPermission(LocalContext.current)


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Greeting("Android")
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        val context = LocalContext.current

        var list = remember { mutableStateListOf<AppFile>() }
        list.addAll(loadPhotoFromInternalStorage(context))

        galleryLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
                uriList.forEach {
                    Controller.saveFileToExternalStorage(
                        context, System.currentTimeMillis().toString(), MediaStore.Images
                            .Media.getBitmap(context.contentResolver, it)
                    )

                }
                list.addAll(loadPhotoFromInternalStorage(context))
            }



        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Hello $name!",

                modifier = modifier,
                fontSize = 20.sp
            )

            Button(onClick = {

                galleryLauncher.launch("image/*").apply {
                }

            }) {
                Text(
                    text = "Read File",
                    modifier = modifier
                )
            }
            Button(onClick = {

            }) {
                Text(
                    text = "Write File",
                    modifier = modifier
                )
            }
            Button(onClick = {
                cameraPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)

            }) {
                Text(
                    text = "Ask Permission",
                    modifier = modifier
                )
            }

            LazyColumn() {
                items(list.size) {
                    Image(bitmap = list[it].bmp.asImageBitmap(), contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                deletePhotoFromInternalStorage(list.get(it).name, context)
                            }
                            .height(200.dp)
                    )
                }
            }
        }
    }

}



