package com.example.fastcampus

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class Melon_detail : AppCompatActivity() {
    val mediaPlayer = MediaHolder.mediaPlayer
    val initialIndex = MediaHolder.prePosition

//    val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_melon_detail)

        val intent = intent


        var nowId: Int = intent.getIntExtra("id", 1)
        val melonItemList =
            intent.getParcelableArrayListExtra<MelonItem>("melonItemList") as ArrayList<MelonItem>

        fun settingDetail(index: Int) {
            findViewById<TextView>(R.id.title).text = melonItemList.get(index).title
            Glide.with(this@Melon_detail).load(melonItemList.get(index).thumbnail).centerCrop()
                .into(findViewById(R.id.thumbnail))

            mediaPlayer.reset() // 리셋을 몰라서 계속 오류 났었는데 ㅠㅠ
            mediaPlayer.setDataSource(melonItemList.get(index).song)
            mediaPlayer.prepare()

            val returnIntent:Intent = Intent()
            returnIntent.putExtra("initialIndex",initialIndex)
            setResult(RESULT_OK,returnIntent)
        }

        fun songStart(mediaPlayer: MediaPlayer) {
            mediaPlayer.start()
        }

        fun songStop(mediaPlayer: MediaPlayer) {
            mediaPlayer.pause()
        }

        fun findIndex(): Int {
            melonItemList.forEachIndexed { index, it ->
                if (it.id == nowId) return index
            }
            return -1
        }

        var nowIndex: Int = findIndex()

        fun nextSong() {
            songStop(mediaPlayer)
            if (nowIndex + 1 < melonItemList.size) {
                nowIndex++
                MediaHolder.prePosition++
            } else {
                nowIndex = 0
                MediaHolder.prePosition = 0
            }
            settingDetail(nowIndex)
            songStart(mediaPlayer)
        }

        fun preSong() {
            songStop(mediaPlayer)
            if (nowIndex > 0) {
                nowIndex--
                MediaHolder.prePosition--
            } else {
                nowIndex = melonItemList.size - 1
                MediaHolder.prePosition = melonItemList.size - 1
            }
            settingDetail(nowIndex)
            songStart(mediaPlayer)
        }

        findViewById<ImageView>(R.id.nextSong).setOnClickListener {
            nextSong()
        }

        findViewById<ImageView>(R.id.preSong).setOnClickListener {
            preSong()
        }

        findViewById<ImageView>(R.id.startPause).setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                findViewById<ImageView>(R.id.startPause).setImageResource(R.drawable.play)
            } else {
                mediaPlayer.start()
                findViewById<ImageView>(R.id.startPause).setImageResource(R.drawable.pause)
            }
        }

        val returnIntent:Intent = Intent()
        returnIntent.putExtra("initialIndex",initialIndex)
        setResult(RESULT_OK,returnIntent)

        if (MediaHolder.prePosition != nowIndex) { // 이전 목록에서 틀던 곡과 다르면 다른 곡 틀게 만들기
            settingDetail(nowIndex)

            songStart(mediaPlayer)
        } else if (!mediaPlayer.isPlaying) {

            songStart(mediaPlayer)
        }
        findViewById<TextView>(R.id.title).text = melonItemList.get(nowIndex).title
        Glide.with(this@Melon_detail).load(melonItemList.get(nowIndex).thumbnail).centerCrop()
            .into(findViewById(R.id.thumbnail))
        MediaHolder.prePosition = nowIndex
    }

    override fun onStop() {
        super.onStop()
        val returnIntent:Intent = Intent()

            returnIntent.putExtra("changed",true)
            setResult(RESULT_OK,returnIntent)
    }
}



