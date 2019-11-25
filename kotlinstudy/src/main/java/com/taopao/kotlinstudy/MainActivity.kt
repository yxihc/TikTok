package com.taopao.kotlinstudy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.taopao.kotlinstudy.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        tv_holle.setText("萨顶顶撒")

        tv_holle.setOnClickListener(this)

        btn_ss.setText("大飒飒")
    }



    fun texts(psd: String?): String {
        var name = "撒的撒"
        name.equals("1", false)


        var a = 1
        var b = 2

        var c = if (a > b) a else b;

        if (1 == 1) {

        } else {

        }

        for (index in 1..10 step 1) {


        }

        var list = listOf<Int>(1, 2, 3, 3)


        for ((index, value) in list.withIndex()) {


        }


        val hashMapOf = hashMapOf<String, String>()
        hashMapOf.put("ss", "打")

        val get = hashMapOf.get("ss")


        juming(yao = 115)

        return name
    }


    var pi = 45455454;
    fun juming(moren: Int = pi, yao: Int) {

    }

    fun juming(xxxx: Int) {

        var  ax=TestB("是谁的")
    }

    override fun onClick(v: View?) {
    }
}
