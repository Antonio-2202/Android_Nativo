package com.example.learning_andriod

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learning_andriod.dao.FakeGmailDao
import com.example.learning_andriod.dao.FakePhotoDao
import com.example.learning_andriod.databinding.ActivityPhotoBinding
import com.example.learning_andriod.domain.GmailItem
import com.example.learning_andriod.domain.PhotoItem
import com.example.learning_andriod.singletons.GmailRepository
import com.example.learning_andriod.singletons.PhotoRepository
import java.util.UUID

class PhotoActivity : ComponentActivity() {
    private lateinit var binding: ActivityPhotoBinding
    private lateinit var adapter: PhotoAdapter
    private val photoRepository: FakePhotoDao = PhotoRepository.getInstance()
    private var photos: List<PhotoItem> = photoRepository.getAllPhotos()
    private lateinit var takePictureLauncher: ActivityResultLauncher<Intent>
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PhotoAdapter(photos.toMutableList())
        binding.photoList.adapter = adapter
        binding.photoList.layoutManager = GridLayoutManager(this, 2)

        takePictureLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val imageBitmap = data?.extras?.getParcelable<Bitmap>("data")
                if (imageBitmap != null) {
                    val photoItem =
                        PhotoItem(id = UUID.randomUUID().toString(), photoUrl = imageBitmap)
                    photoRepository.addPhoto(photoItem)
                    adapter.updatePhotos(photos)
                }
            }
        }

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureLauncher.launch(takePictureIntent)
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
            }
        }

        binding.photoAdd.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    takePictureLauncher.launch(takePictureIntent)
                }

                ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                ) -> {
                    Toast.makeText(
                        this,
                        "La aplicación necesita acceso a la cámara para tomar fotos.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        }

    }
}