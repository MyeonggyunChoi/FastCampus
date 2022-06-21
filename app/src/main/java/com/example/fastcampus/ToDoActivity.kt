package com.example.fastcampus

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.to_do_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        findViewById<ImageView>(R.id.write).setOnClickListener {
            startActivity(
                Intent(this@ToDoActivity, ToDoWriteActivity::class.java)
            )
        }

        getToDoList()

        findViewById<EditText>(R.id.searchKeyword).doAfterTextChanged {
            searchToDoList(it.toString())
        }
    }

    fun makeToDoList(todoMutableList: MutableList<ToDoTime>, header:HashMap<String,String>){
        val adapter = RectclerViewAdapter(
            todoMutableList,
            LayoutInflater.from(this@ToDoActivity),
            header
        )

        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
    }

    fun searchToDoList(keyword:String){
        var previousDate:String = ""
        val todoMutableList:MutableList<ToDoTime> = mutableListOf<ToDoTime>()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        val header = HashMap<String, String>()
        val sp = this.getSharedPreferences(
            "user_info",
            Context.MODE_PRIVATE
        )
        val token = sp.getString("token", "")
        header.put("Authorization", "token " + token!!)

        retrofitService.seerachToDoList(header,keyword).enqueue(object : Callback<ArrayList<ToDo>> {
            override fun onResponse(
                call: Call<ArrayList<ToDo>>,
                response: Response<ArrayList<ToDo>>
            ) {
                if (response.isSuccessful) {
                    val todoList = response.body()

                    todoList?.forEach {
                        val tmpDate = it.created.split("T")[0]

                        if (previousDate == tmpDate) {
                            todoMutableList.add(ToDoTime("",it))
                        } else {
                            previousDate = tmpDate
                            todoMutableList.add(ToDoTime(tmpDate,it))
                            todoMutableList.add(ToDoTime("",it))
                        }
                    }

                    makeToDoList(todoMutableList,header)
                }
            }

            override fun onFailure(call: Call<ArrayList<ToDo>>, t: Throwable) {

            }
        })
    }

    fun getToDoList() {
        var previousDate:String = ""
        val todoMutableList:MutableList<ToDoTime> = mutableListOf<ToDoTime>()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        val header = HashMap<String, String>()
        val sp = this.getSharedPreferences(
            "user_info",
            Context.MODE_PRIVATE
        )
        val token = sp.getString("token", "")
        header.put("Authorization", "token " + token!!)

        retrofitService.getToDoList(header).enqueue(object : Callback<ArrayList<ToDo>> {
            override fun onResponse(
                call: Call<ArrayList<ToDo>>,
                response: Response<ArrayList<ToDo>>
            ) {
                if (response.isSuccessful) {
                    val todoList = response.body()

                    todoList?.forEach {
                        val tmpDate = it.created.split("T")[0]

                        if (previousDate == tmpDate) {
                            todoMutableList.add(ToDoTime("",it))
                        } else {
                            previousDate = tmpDate
                            todoMutableList.add(ToDoTime(tmpDate,it))
                            todoMutableList.add(ToDoTime("",it))
                        }
                    }

                    makeToDoList(todoMutableList,header)
                }
            }

            override fun onFailure(call: Call<ArrayList<ToDo>>, t: Throwable) {

            }
        })
    }
}

class RectclerViewAdapter(
    val todoList: MutableList<ToDoTime>,
    val inflater: LayoutInflater,
    val header:HashMap<String,String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox

        init {
            checkBox = itemView.findViewById(R.id.checkbox)
            checkBox.apply {
                this.setOnClickListener {
                    val retrofit = Retrofit.Builder()
                        .baseUrl("http://mellowcode.org/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val retrofitService = retrofit.create(RetrofitService::class.java)

                    retrofitService.changeToDoComplte(header,todoList.get(adapterPosition).todo.id).enqueue(object : Callback<Any>{
                        override fun onResponse(call: Call<Any>, response: Response<Any>) {

                        }

                        override fun onFailure(call: Call<Any>, t: Throwable) {

                        }
                    })
                }
            }
        }
    }

    inner class DateViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val dateTaxtView:TextView

        init {
            dateTaxtView = itemView.findViewById(R.id.dateTextView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val todo = todoList.get(position)
        when(todo.date){
            "" -> return 0
            else -> return 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            1 -> return DateViewHolder(inflater.inflate(R.layout.to_do_item_time, parent, false))
            else -> return ContentViewHolder(inflater.inflate(R.layout.to_do_item,parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DateViewHolder){
            holder.dateTaxtView.text = todoList.get(position).todo.created.split("T")[0]
        }

        if (holder is ContentViewHolder){
            holder.checkBox.isChecked = todoList.get(position).todo.is_complete
            holder.checkBox.text = todoList.get(position).todo.content
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}

class ToDoTime(
    val date:String,
    val todo:ToDo
)