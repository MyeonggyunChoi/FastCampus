package com.example.fastcampus

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentFirst():Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { //return type이 View임
        // inflater : XML을 화면에 사용할 준비를 한다 (부풀리다) = (XML을 View로 만들어 준다)
        // container : Fragment에서 사용될 XML의 부모뷰
        val view = inflater.inflate(R.layout.first_fragment, container, false)
        // attachToRoot : 루트에 붙일지 말지 (X) 언제 붙을지!! (O)
            // true : 바로 붙음
            // false : 바로 붙진 않음

        (view.findViewById<TextView>(R.id.call_activity)).setOnClickListener{
            this.printTestLog()
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data:String? = arguments?.getString("key")
        Log.d("testt","data : " + data)
    }

    fun printTestLog(){
        Log.d("testt","print test log fragment")
    }
}