package com.example.fastcampus

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File
import java.lang.Exception

class InstarPostUploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_post_upload)

        findViewById<ImageView>(R.id.postImage).apply {

            var startActivityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                when (it.resultCode) {
                    RESULT_OK -> {
                        // 이미지 적용
                        var currentImageUrl: Uri? = it.data?.data

                        try {
                            val bitmap =
                                MediaStore.Images.Media.getBitmap(contentResolver, currentImageUrl)
                            this.setImageBitmap(bitmap)
                        } catch (e: Exception) {
                            Log.d("instarr", "" + e)
                        }
                    }
                }
            }

            this.setOnClickListener {
                val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startActivityLauncher.launch(intent)
            }
        }

        findViewById<TextView>(R.id.cancel).apply {
            this.setOnClickListener {
                finish()
            }
        }

        findViewById<TextView>(R.id.upload).setOnClickListener {

        }

    }

    private fun getRealFile(uri: Uri): File? {
        var uri: Uri? = uri
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        if (uri == null) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        var cursor: Cursor? = this.getContentResolver().query(
            uri!!,
            projection,
            null,
            null,
            MediaStore.Images.Media.DATE_MODIFIED + " desc"
        )
        if (cursor == null || cursor.getColumnCount() < 1) {
            return null
        }
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val path: String = cursor.getString(column_index)
        if (cursor != null) {
            cursor.close()
            cursor = null
        }
        return File(path)
    }
}