package com.example.fastcampus

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.melon_item.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class InstarPostFragment : Fragment() {
    var imageUri: Uri? = null
    var contentInput: String = ""
    lateinit var postContent: EditText
    lateinit var postImageView: ImageView
    lateinit var upload: TextView
    lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.instar_post_fragment, container, false)
    }

    fun makePost() {
        imagePickerLauncher.launch(
            Intent(Intent.ACTION_PICK).apply {
                this.type = MediaStore.Images.Media.CONTENT_TYPE
            }
        )

        postContent.doAfterTextChanged {
            contentInput = it.toString()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

//        view.findViewById<TextView>(R.id.upload).setOnClickListener {
        upload.setOnClickListener {
            val file = getRealFile(imageUri!!) // 실제 파일을 가져옴
            val requestFile = RequestBody.create(
                MediaType.parse(
                    (activity as InstarMainActivity).contentResolver.getType(imageUri!!) // 올릴 파일의 타입을 알려주는 부분
                ), file // 실제 파일
            )

            val body = MultipartBody.Part.createFormData(
                "image",
                file!!.name,
                requestFile
            ) // 파일의 key값을 정해주는 부분
            val content = RequestBody.create(MultipartBody.FORM, contentInput)
            val header = HashMap<String, String>()
            val sp = (activity as InstarMainActivity).getSharedPreferences(
                "instagram",
                Context.MODE_PRIVATE
            )
            val token = sp.getString("token", "")
            header.put("Authorization", "token " + token!!)
            retrofitService.uploadPost(header, body, content).enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    Log.d("instarr", "success")
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.d("instarr", "fail")
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("instarr", "1")

        postImageView = view.findViewById(R.id.postImage)
        postContent = view.findViewById(R.id.postContent)
        upload = view.findViewById(R.id.upload)

        //       val postImageView = view.findViewById<ImageView>(R.id.postImage)
        val glide = Glide.with(activity as InstarMainActivity)
        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                imageUri = it.data!!.data
                glide.load(imageUri).into(postImageView)
            }

        postImageView.apply {
            this.setOnClickListener {
                makePost()
                this.isEnabled = false
            }
        }
    }


    private fun getRealFile(uri: Uri): File? {
        var uri: Uri? = uri
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        if (uri == null) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        var cursor: Cursor? = (activity as InstarMainActivity).getContentResolver().query(
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