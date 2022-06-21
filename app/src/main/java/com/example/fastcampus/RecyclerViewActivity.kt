package com.example.fastcampus

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.log

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val carList = mutableListOf<Car>()
        for (i in 0..100) {
            carList.add(Car("" + i + "번째 자동차", "" + i + "번째 엔진"))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // 리사이클러 뷰에 어답터 장착
        recyclerView.adapter = RecyclerViewAdapter(carList, LayoutInflater.from(this), this)
        // 리사이클러 뷰에 레이아웃 매니저 장착
        recyclerView.layoutManager = LinearLayoutManager(this) // -> 세로방향 (기본값)
//        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false) // -> 기본방향
//        recyclerView.layoutManager = GridLayoutManager(this, 2) // -> 그리드 형식, spanCount는 열 개수
    }
}

class RecyclerViewAdapter( // outer class
    var carList: MutableList<Car>,
    var inflater: LayoutInflater,
    val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() { // RecylcleView용 adapter을 상속받아 사용 -> 성능 good

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // inner class
        // 아이템 뷰의 상세 뷰 컴포넌트를 홀드함
        var carImage: ImageView
        var nthCar: TextView
        var nthEngine: TextView

        init {
            carImage = itemView.findViewById(R.id.carImage)
            nthCar = itemView.findViewById(R.id.nthCar)
            nthEngine = itemView.findViewById(R.id.nthEngine)

            // 클릭 리스너 장착
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val car = carList.get(position)
                Log.d("testt",car.nthCar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // 아이템 뷰를 리턴
        val view = inflater.inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 데이터를 아이템 뷰의 뷰 컴포넌트와 묶는다(뷰를 채워준다.)
        if (holder is ViewHolder) {
            holder.carImage.setImageDrawable(context.resources.getDrawable(R.drawable.back))
            holder.nthCar.text = carList.get(position).nthCar
            holder.nthEngine.text = carList.get(position).nthEngine
        }
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("testt",carList.get(position).nthCar)
        Log.d("testt",""+super.getItemViewType(position))
        return super.getItemViewType(position)
    }
}