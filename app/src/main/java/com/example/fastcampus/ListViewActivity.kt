package com.example.fastcampus

import android.content.Context
import android.os.BaseBundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        // 데이터 준비
        var carList = mutableListOf<Car>()
        for (i in 0..100) {
            carList.add(Car("" + i + "번째 자동차", "" + i + "번째 엔진"))
        }

        // 어답터 준비
        val adapter = ListViewAdapter(
            carList,
            LayoutInflater.from(this),
            this
        )

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val car: Car = adapter.carList.get(position)
            val nthCar = car.nthCar
            val nthEngine = car.nthEngine

            Toast.makeText(this, nthCar + " " + nthEngine, Toast.LENGTH_SHORT).show()
        }

        // 데이터 갱신
        findViewById<TextView>(R.id.addCar).setOnClickListener{
            adapter.carList.add(
                Car("new car","new engine")
            )
            adapter.notifyDataSetChanged()
        }
    }
}

class ListViewAdapter(
    val carList: MutableList<Car>,
    val layoutInflater: LayoutInflater,
    val context: Context
) : BaseAdapter() {
    override fun getCount(): Int {
        // 전체 데이터의 크기(갯수) 리턴
        return carList.size
    }

    override fun getItem(position: Int): Any {
        // 전체 데이터 중에서 해당번째(position)의 데이터를 리턴
        return carList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong() // 잘 사용 안함
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // 데이터와 xml 매칭시키는 부분
        // 해당 번째 뷰를 리턴

        val view: View
        val holder: ViewHolder

        if (convertView == null) { // convertView는 재활용될 때, 들어오는 뷰 -> 즉 null이면 처음 사용하는 뷰라는 것
//            Log.d("testt",""+position)
            view = layoutInflater.inflate(R.layout.car_item, null) // -> 결과적으로 얘를 재사용 하는 것
            holder = ViewHolder()
            holder.carImage = view.findViewById<ImageView>(R.id.carImage)
            holder.nthCar = view.findViewById<TextView>(R.id.nthCar)
            holder.nthEngine = view.findViewById<TextView>(R.id.nthEngine)

            view.tag = holder
        } else { //재활용
//            Log.d("testt","recycle : "+position)
            holder = convertView.tag as ViewHolder
            view = convertView
        }
        val car = carList.get(position)
        holder.carImage?.setImageDrawable(
            context.resources.getDrawable(
                R.drawable.round_rectangle_blue,
                context.theme
            )
        )
        holder.nthCar?.text = car.nthCar
        holder.nthEngine?.text = car.nthEngine

        // ↓ 일일이 inflater하기 때문에 비효율적인 방식
//        val view = layoutInflater.inflate(R.layout.car_item, null)
//        val carImage = view.findViewById<ImageView>(R.id.carImage)
//        val nthCar = view.findViewById<TextView>(R.id.nthCar)
//        val nthEngine = view.findViewById<TextView>(R.id.nthEngine)
//
//        val car = carList.get(position)
//        carImage.setImageDrawable(
//            context.resources.getDrawable(
//                R.drawable.round_rectangle_blue,
//                context.theme
//            )
//        )
//        nthCar.text = car.nthCar
//        nthEngine.text = car.nthEngine

        return view
    }
}

class ViewHolder {
    var carImage: ImageView? = null
    var nthCar: TextView? = null
    var nthEngine: TextView? = null
}