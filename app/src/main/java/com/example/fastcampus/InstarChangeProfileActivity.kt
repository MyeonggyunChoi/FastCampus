package com.example.fastcampus

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_intent_two.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class InstarChangeProfileActivity : AppCompatActivity() {
    var imageUri: Uri? = null
    var glide: RequestManager? = null
    lateinit var imageView: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_change_profile)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)


        imageView = findViewById(R.id.profileImage)
        glide = Glide.with(this)

        val imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                imageUri = it.data!!.data
                glide!!.load(imageUri).into(imageView)
            }
        imagePickerLauncher.launch(
            Intent(Intent.ACTION_PICK).apply {
                this.type = MediaStore.Images.Media.CONTENT_TYPE
            }
        )

        findViewById<TextView>(R.id.change_img).setOnClickListener {
            val file = getRealFile(imageUri!!) // 실제 파일을 가져옴
            val requestFile = RequestBody.create(
                MediaType.parse(
                    this.contentResolver.getType(imageUri!!) // 올릴 파일의 타입을 알려주는 부분
                ), file // 실제 파일
            )

            val body = MultipartBody.Part.createFormData(
                "image",
                file!!.name,
                requestFile
            ) // 파일의 key값을 정해주는 부분
            val header = HashMap<String, String>()
            val sp = this.getSharedPreferences(
                "instagram",
                Context.MODE_PRIVATE
            )
            val token = sp.getString("token", "")
            header.put("Authorization", "token " + token!!)

            val userId = sp.getString("userId", "")
            val user = RequestBody.create(MultipartBody.FORM, userId)
            retrofitService.changeProfile(userId!!.toInt(), header, body, user)
                .enqueue(object : Callback<Any> {
                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@InstarChangeProfileActivity,
                                "변경 완료",
                                Toast.LENGTH_SHORT
                            ).show()
                            onBackPressed()
                        }
                    }

                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        Toast.makeText(
                            this@InstarChangeProfileActivity,
                            "변경 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
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