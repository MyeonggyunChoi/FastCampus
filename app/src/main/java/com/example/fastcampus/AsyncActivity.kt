package com.example.fastcampus

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        val backgroundAsyncTask = BackgroundAsyncTask(
        findViewById(R.id.progressBar),
        findViewById(R.id.progressBarText)
        )

        (findViewById<TextView>(R.id.start)).setOnClickListener{
            backgroundAsyncTask.execute()
        }

        (findViewById<TextView>(R.id.pause)).setOnClickListener{
            backgroundAsyncTask.cancel(true)
        }

        (findViewById<TextView>(R.id.shoot)).setOnClickListener{
            Log.d("testt","shoot")
        }
    }
}


class BackgroundAsyncTask(
    val progressbar: ProgressBar,
    val progressText: TextView
) : AsyncTask<Int, Int, Int>() {
    // params, Progress, Result
    // params -> doInbackground에서 사용할 타입
    // progress -> onProgressUpdate에서 사용할 타입
    // result -> onPostExcute에서 사용할 타입

    // deprecates -> 더 이상 사용을 권장하지 않음
    // 코루틴으로 대체 가능 -> 멀티 태스킹, 동기 / 비동기 처리

    var percent: Int = 0
    override fun doInBackground(vararg params: Int?): Int {
        while (isCancelled() == false) {
            percent++
            if (percent > 100) break
            else {
                publishProgress(percent)
            }
            Thread.sleep(100)
        }
        return percent
    }

    override fun onPreExecute() {
        percent = 0
        progressbar.setProgress(percent)
    }

    override fun onPostExecute(result: Int?) {
        progressText.text = "작업 완료"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        progressbar.setProgress(values[0]?:0)
        progressText.text = "퍼센트 : " + values[0]
    }

    override fun onCancelled() {
        progressbar.setProgress(0)
        progressText.text = "cancled"
    }
}