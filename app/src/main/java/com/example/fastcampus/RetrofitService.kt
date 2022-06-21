package com.example.fastcampus

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

class StudentFromServer(
    val id: Int, val name: String, val age: Int, val intro: String
) {
    constructor(name: String, age: Int, intro: String) : this(0, name, age, intro)
}

class YoutubeItem(
    val id: Int, val title: String, val content: String, val video: String, val thumbnail: String
)

@Parcelize
class MelonItem(
    val id: Int, val title: String, val song: String, val thumbnail: String
) : Parcelable

class UserToken(
    val username: String, val token: String, val id:Int
)

class OwnerProfile(
    val username: String, val image: String
)

class PostItem(
    val id: Int,
    val created: String,
    val content: String,
    val image: String,
    val like_count: Int,
    val owner_profile: OwnerProfile
)

class UserInfo(
    val id:Int,
    val username: String,
    val profile: OwnerProfile
)

class ToDo(
    val id: Int,
    val content: String,
    val is_complete: Boolean,
    val created: String
)

interface RetrofitService {

    // 투두리스트 과제 떄 -----------------------------------------------------
    @GET("to-do/search/")
    fun seerachToDoList(
        @HeaderMap headers: Map<String, String>,
        @Query("keyword") keyword: String
    ): Call<ArrayList<ToDo>>

    @PUT("to-do/complete/{todoId}")
    fun changeToDoComplte(
        @HeaderMap headers: Map<String, String>,
        @Path("todoId") todoId: Int
    ): Call<Any>


    @GET("to-do/")
    fun getToDoList(
        @HeaderMap headers: Map<String, String>,
    ): Call<ArrayList<ToDo>>

    @POST("to-do/")
    @FormUrlEncoded
    fun makeToDo(
        @HeaderMap headers: Map<String, String>,
        @FieldMap params: HashMap<String, Any>
    ): Call<Any>
    //------------------------------------------------------------------



    // 인스타그램 과제 떄 -----------------------------------------------------
    @Multipart
    @PUT("user/profile/{user_id}/")
    fun changeProfile(
        @Path("user_id") userId : Int,
        @HeaderMap headers: Map<String, String>,
        @Part image: MultipartBody.Part,
        @Part("user") user:RequestBody
    ):Call<Any>


    @GET("user/userInfo/")
    fun getUserInfo(
        @HeaderMap headers: Map<String, String>
    ):Call<UserInfo>


    @Multipart // 사진, 파일을 올릴 때 용량이 크니 조각조각 보낸다고 생각 ㄱㄱ
    @POST("instagram/post/")
    fun uploadPost(
        @HeaderMap headers: Map<String, String>,
        @Part image: MultipartBody.Part,
        @Part("content") content: RequestBody
    ):Call<Any>

    @POST("instagram/post/like/{post_id}/")
    fun postLike(
        @Path("post_id") post_id: Int
    ): Call<Any>


    @GET("instagram/post/list/all/")
    fun getPosts(): Call<ArrayList<PostItem>>


    @POST("user/signup/")
    @FormUrlEncoded
    fun instaJoin(
        @FieldMap params: HashMap<String, Any> /* = java.util.HashMap<kotlin.String, kotlin.Any> */
    ): Call<UserToken>


    @POST("user/login/")
    @FormUrlEncoded
    fun instaLogin(
        @FieldMap params: HashMap<String, Any>
    ): Call<UserToken>

    //------------------------------------------------------------------

    // 멜론 과제 떄 -----------------------------------------------------
    @GET("melon/list/")
    fun getMelonItemList(): Call<ArrayList<MelonItem>>

    //------------------------------------------------------------------

    // 유튜브 과제 떄 -----------------------------------------------------
    @GET("youtube/list/")
    fun getYoutubeItemList(): Call<ArrayList<YoutubeItem>>

    //------------------------------------------------------------------

    // Retrofit 강의 때 사용한 부분 -----------------------------------------
    @GET("json/students")
    fun getStudentList(): Call<ArrayList<StudentFromServer>>

    @POST("json/students/")
    fun createStudent(
        @Body params: HashMap<String, Any>
    ): Call<StudentFromServer>

    @POST("json/students/")
    fun easyCreateStudent(
        @Body student: StudentFromServer
    ): Call<StudentFromServer>
    //------------------------------------------------------------------
}