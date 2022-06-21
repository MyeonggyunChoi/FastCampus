package com.example.fastcampus

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_assignmet4.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.zip.Inflater

class MelonActivity : AppCompatActivity() {
    val mediaPlayer = MediaHolder.mediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_melon)

        val startPauseImageView = findViewById<ImageView>(R.id.startPause)

        val startActivityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val initialIndex = it.data?.getIntExtra("initialIndex", MediaHolder.prePosition)
            if (it.resultCode == RESULT_OK) {
                // 상세폼에서 이동했을 경우 목록도 그에 맞춰서 변경
                MediaHolder.itemViewList.get(initialIndex!!)
                    .findViewById<ImageView>(R.id.itemPlayPause).setImageResource(R.drawable.play)

                if (MediaHolder.mediaPlayer.isPlaying) {
                    MediaHolder.itemViewList.get(MediaHolder.prePosition)
                        .findViewById<ImageView>(R.id.itemPlayPause)
                        .setImageResource(R.drawable.pause)
                    startPauseImageView.setImageResource(R.drawable.pause)
                } else {
                    startPauseImageView.setImageResource(R.drawable.play)
                }
            }
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getMelonItemList().enqueue(object : Callback<ArrayList<MelonItem>> {
            override fun onResponse(
                call: Call<ArrayList<MelonItem>>,
                response: Response<ArrayList<MelonItem>>
            ) {
                if (response.isSuccessful) {
                    val melonItemList = response.body()
                    val glide = Glide.with(this@MelonActivity)
                    val adapter = MelonListAdapter(
                        melonItemList!!,
                        LayoutInflater.from(this@MelonActivity),
                        glide,
                        this@MelonActivity,
                        startPauseImageView,
                        startActivityLauncher
                    )
                    findViewById<RecyclerView>(R.id.melonRecyclerView).adapter = adapter

                    fun nextSong() {
                        if (mediaPlayer.isPlaying) mediaPlayer.pause()

                        MediaHolder.itemViewList.get(MediaHolder.prePosition)
                            .findViewById<ImageView>(R.id.itemPlayPause)
                            .setImageResource(R.drawable.play)
                        if (MediaHolder.prePosition + 1 < melonItemList.size) {
                            MediaHolder.prePosition++
                        } else {
                            MediaHolder.prePosition = 0
                        }
                        MediaHolder.itemViewList.get(MediaHolder.prePosition)
                            .findViewById<ImageView>(R.id.itemPlayPause)
                            .setImageResource(R.drawable.pause)
                        startPauseImageView.setImageResource(R.drawable.pause)
                        mediaPlayer.reset()
                        mediaPlayer.setDataSource(melonItemList.get(MediaHolder.prePosition).song)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                    }

                    fun preSong() {
                        if (mediaPlayer.isPlaying) mediaPlayer.pause()

                        MediaHolder.itemViewList.get(MediaHolder.prePosition)
                            .findViewById<ImageView>(R.id.itemPlayPause)
                            .setImageResource(R.drawable.play)
                        if (MediaHolder.prePosition > 0) {
                            MediaHolder.prePosition--
                        } else {
                            MediaHolder.prePosition = melonItemList.size - 1
                        }
                        MediaHolder.itemViewList.get(MediaHolder.prePosition)
                            .findViewById<ImageView>(R.id.itemPlayPause)
                            .setImageResource(R.drawable.pause)
                        startPauseImageView.setImageResource(R.drawable.pause)
                        mediaPlayer.reset()
                        mediaPlayer.setDataSource(melonItemList.get(MediaHolder.prePosition).song)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                    }

                    findViewById<ImageView>(R.id.nextSong).setOnClickListener {
                        nextSong()
                    }

                    findViewById<ImageView>(R.id.preSong).setOnClickListener {
                        preSong()
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<MelonItem>>, t: Throwable) {
                Log.d("melon1", "fail")
            }


        })

        findViewById<ImageView>(R.id.startPause).apply {


            this.setOnClickListener {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    MediaHolder.itemViewList.get(MediaHolder.prePosition)
                        .findViewById<ImageView>(R.id.itemPlayPause)
                        ?.setImageResource(R.drawable.play)
                    this.setImageResource(R.drawable.play)
                } else {
                    mediaPlayer.start()
                    MediaHolder.itemViewList.get(MediaHolder.prePosition)
                        .findViewById<ImageView>(R.id.itemPlayPause)
                        ?.setImageResource(R.drawable.pause)
                    this.setImageResource(R.drawable.pause)
                }
            }
        }
    }
}

class MelonListAdapter(
    val melonItemList: ArrayList<MelonItem>,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val context: Context,
    val startPauseImageView: ImageView,
    val startActivityLauncher: ActivityResultLauncher<Intent>
) : RecyclerView.Adapter<MelonListAdapter.ViewHolder>() {
    val mediaPlayer = MediaHolder.mediaPlayer

    inner class ViewHolder(itemView: View, parent: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val thumbnail: ImageView

        init {
            title = itemView.findViewById(R.id.title)
            thumbnail = itemView.findViewById(R.id.thumbnail)

            itemView.setOnClickListener {
                val intent = Intent(context, Melon_detail::class.java)
                intent.putExtra("id", melonItemList.get(adapterPosition).id)
                intent.putExtra("melonItemList", melonItemList)
                startActivityLauncher.launch(intent)
            }

            itemView.findViewById<ImageView>(R.id.itemPlayPause).setOnClickListener {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()

                    if (MediaHolder.prePosition != adapterPosition) {
                        newSongStart(itemView, adapterPosition)

                        MediaHolder.itemViewList.get(MediaHolder.prePosition)
                            .findViewById<ImageView>(R.id.itemPlayPause)
                            .setImageResource(R.drawable.play)

                        MediaHolder.prePosition = adapterPosition

                        mediaPlayer.start()
                    } else {
                        itemView.findViewById<ImageView>(R.id.itemPlayPause)
                            .setImageResource(R.drawable.play)
                        startPauseImageView.setImageResource(R.drawable.play)
                    }

                } else {
                    if (MediaHolder.prePosition != adapterPosition) {
                        newSongStart(itemView, adapterPosition)

                        MediaHolder.prePosition = adapterPosition
                    }
                    mediaPlayer.start()

                    itemView.findViewById<ImageView>(R.id.itemPlayPause)
                        .setImageResource(R.drawable.pause)
                    // 목록에 있는 폼에서도 플레이로 변경
                    startPauseImageView.setImageResource(R.drawable.pause)
                }
            }
        }
    }

    fun newSongStart(itemView: View, adapterPosition: Int) {
        itemView.findViewById<ImageView>(R.id.itemPlayPause)
            .setImageResource(R.drawable.pause)
        mediaPlayer.reset() // 리셋을 몰라서 계속 오류 났었는데 ㅠㅠ
        mediaPlayer.setDataSource(melonItemList.get(adapterPosition).song)
        mediaPlayer.prepare()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.melon_item, parent, false)
        return ViewHolder(view, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = melonItemList.get(position).title
        glide.load(melonItemList.get(position).thumbnail).centerCrop().into(holder.thumbnail)
        MediaHolder.itemViewList.add(holder.itemView) // 뷰 저장
        if (position == 0) {
            MediaHolder.prePosition = 0
            mediaPlayer.reset() // 리셋을 몰라서 계속 오류 났었는데 ㅠㅠ
            mediaPlayer.setDataSource(melonItemList.get(0).song)
            mediaPlayer.prepare()
        }
    }

    override fun getItemCount(): Int {
        return melonItemList.size
    }
}

class MediaHolder() {
    companion object {
        val mediaPlayer = MediaPlayer()
        var prePosition = -1
        val itemViewList = mutableListOf<View>()
    }
}