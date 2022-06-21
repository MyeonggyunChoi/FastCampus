package com.example.fastcampus

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_instar.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstarPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_page)

        // 메뉴 바 불러오기
        val fragmentManager = supportFragmentManager
        val fragmentMenu = InstarMenuFragment()
        val transaction = fragmentManager.beginTransaction()

        val bundle:Bundle = Bundle()
        bundle.putString("host","home")
        fragmentMenu.arguments = bundle

        transaction.replace(R.id.menuRoot, fragmentMenu, "fragment_menu_tag")
        transaction.commit()

        // 스크롤 불러오기
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.getPosts().enqueue(object : Callback<ArrayList<PostItem>> {
            override fun onResponse(
                call: Call<ArrayList<PostItem>>,
                response: Response<ArrayList<PostItem>>
            ) {
                if (response.code() == 200) {
                    val instarItemList = response.body() as ArrayList<PostItem>
                    val glide = Glide.with(this@InstarPageActivity)
                    val adapter = RecyclerViewAdapter(
                        instarItemList,
                        LayoutInflater.from(this@InstarPageActivity),
                        glide,
                        this@InstarPageActivity
                    )

                    findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<PostItem>>, t: Throwable) {
                Log.d("instarr", "failed")
            }
        })
    }

    class RecyclerViewAdapter(
        val itemList: ArrayList<PostItem>,
        val inflater: LayoutInflater,
        var glide: RequestManager,
        val context: Context
    ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
        inner class ViewHolder(itmeView: View) : RecyclerView.ViewHolder(itmeView) {
            val ownerImageView: CircleImageView
            val ownerNameTextView: TextView
            val postImageView: ImageView
            val postContentTextView: TextView
            val likeCnt: TextView
            val registedDate: TextView

            init {
                ownerImageView = itmeView.ownerImage
                ownerNameTextView = itmeView.ownerName
                postImageView = itmeView.postImage
                postContentTextView = itmeView.postContent
                likeCnt = itmeView.likeCnt
                registedDate = itmeView.registedDate

                var doubleClickWait: Long = 0

                itmeView.setOnClickListener {
                    if (System.currentTimeMillis() - doubleClickWait >= 1000) {
                        doubleClickWait = System.currentTimeMillis()
                    } else {
                        val postScreen = it.findViewById<ImageView>(R.id.postScreen)
                        val like = it.findViewById<ImageView>(R.id.like)
                        postScreen.visibility = View.VISIBLE
                        like.visibility = View.VISIBLE
                        Handler(Looper.getMainLooper()).postDelayed({
                            postScreen.visibility = View.INVISIBLE
                            like.visibility = View.INVISIBLE
                        }, 500)

                        postLike(itemList.get(adapterPosition).id)
                        likeCnt.text = (likeCnt.text.toString().toInt() + 1).toString()
                    }

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.item_instar, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (itemList.get(position).owner_profile.image != "") {
                glide.load(itemList.get(position).owner_profile.image).centerCrop()
                    .into(holder.ownerImageView)
            }
            if (itemList.get(position).image != "") {
                glide.load(itemList.get(position).image).centerCrop().into(holder.postImageView)
            }
            if (itemList.get(position).owner_profile.username != "") {
                holder.ownerNameTextView.text = itemList.get(position).owner_profile.username
            }
            if (itemList.get(position).content != "") {
                holder.postContentTextView.text = itemList.get(position).content
            }

            holder.likeCnt.text = itemList.get(position).like_count.toString()
            holder.registedDate.text = itemList.get(position).created.subSequence(0, 10)
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

        fun postLike(post_id: Int) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://mellowcode.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val retrofitService = retrofit.create(RetrofitService::class.java)

            retrofitService.postLike(post_id).enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    Log.d("instarr", "like success")
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.d("instarr", "like failed")
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}