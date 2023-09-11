package com.example.storage

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storage.ui.theme.StorageTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.example.storage.Controller.Companion.deletePhotoFromInternalStorage
import com.example.storage.Controller.Companion.loadPhotoFromInternalStorage
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

lateinit var cameraPermissionLauncher: ActivityResultLauncher<String>

lateinit var galleryLauncher: ManagedActivityResultLauncher<String, List<@JvmSuppressWildcards Uri>>

class MainActivityExternalStorage : ComponentActivity() {

    var activityResult = mutableListOf<ActivityResultLauncher<Intent>>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var result =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                println("registerForActivityResult2 New Doc ${result.resultCode} ${result.data}")
                println("  ${result.data?.extras}  ")

                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let {
                        println("registerForActivityResult  openOutputStream ${it}")
                        contentResolver.openOutputStream(it)?.apply {
                            val text = "1. Some random Text \n 2. ${
                                SimpleDateFormat("dd-MM-yyy hh:mm").format(
                                    Date(System.currentTimeMillis())
                                )
                            }"
                            write(text.encodeToByteArray())
                        }
                    };

                }
            }

        activityResult.add(result)

          result =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                println("registerForActivityResult2 Read Doc ${result.resultCode} ${result.data}")
                println("registerForActivityResult3 ${result.data?.extras}  ")

                if (result.resultCode == Activity.RESULT_OK) {
                    result.data?.data?.let {
                        try {
                            println("registerForActivityResult  openInputStream ${it}")
                            contentResolver.openInputStream(it)?.apply {
                                val inputAsString =
                                    bufferedReader().use { it.readText() }  // defaults to UTF-8

                                println("Read File $inputAsString")
                            }
                        } catch (e: Exception) {
                            println("Exception openInputStream $e")

                        }
                    };

                }
            }
        activityResult.add(result)

        loadPhotoFromInternalStorage(baseContext)

        setContent {
            StorageTheme {

                val context = LocalContext.current

                cameraPermissionLauncher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
                        println("cameraPermissionLauncher $isGranted")
                    }


                rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
                    uriList.forEach {
                        //   val bitmap=BitmapFactory.decodeFile(it.path)
                        Controller.saveImageToExternalStorage(
                            context,
                            System.currentTimeMillis().toString(),
                            MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                        )

                    }
                    //  list.addAll(loadPhotoFromInternalStorage(context))
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Storage")
                            },
                            colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Blue)
                        )
                    }, containerColor = Color.White) {

                        Greeting("Android", paddingValues = it)

                    }

                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier, paddingValues: PaddingValues) {
        val context = LocalContext.current

        var list = remember { mutableStateListOf<AppFile>() }
        list.addAll(loadPhotoFromInternalStorage(context))

        galleryLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
                uriList.forEach {
                    val item = context.contentResolver.openInputStream(it)
                    val bytes = item?.readBytes()
                    if (bytes != null) {
                        Controller.writeFileExternalStorage(
                            context, System.currentTimeMillis().toString(), bytes
                        )
                    }

                    /* Controller.saveImageToExternalStorage(
                         context, System.currentTimeMillis().toString(), MediaStore.Images
                             .Media.getBitmap(context.contentResolver, it)
                     )*/

                }
                list.addAll(loadPhotoFromInternalStorage(context))
            }



        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(paddingValues)
        ) {
            Text(
                text = "Hello $name!",

                modifier = modifier, fontSize = 20.sp
            )

            Button(onClick = {

                galleryLauncher.launch("image/*").apply {}

            }) {
                Text(
                    text = "Read File", modifier = modifier
                )
            }
            Button(onClick = {

            }) {
                Text(
                    text = "Write File", modifier = modifier
                )
            }
            Button(onClick = {
                cameraPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)

            }) {
                Text(
                    text = "Ask Permission", modifier = modifier
                )
            }
            Button(onClick = {


                val intent = Intent()
                intent.action = Intent.ACTION_CREATE_DOCUMENT
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TITLE, "MyRandom File.docx")
                println("registerForActivityResult1")
                intent.putExtra("requestCode", 0);
                activityResult[0].launch(intent)

            }) {
                Text(
                    text = "Intent New File", modifier = modifier
                )
            }

            Button(onClick = {
                val intent = Intent()
                intent.action = Intent.ACTION_GET_CONTENT
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.type = "text/plain"
                intent.putExtra("requestCode", 1);
                activityResult[1].launch(intent)

            }) {
                Text(
                    text = "Intent Read File", modifier = modifier
                )
            }

            LazyColumn() {
                items(list.size) {
                    Image(bitmap = list[it].bmp.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                deletePhotoFromInternalStorage(list.get(it).name, context)
                            }
                            .height(200.dp))
                }
            }
        }
    }

}



