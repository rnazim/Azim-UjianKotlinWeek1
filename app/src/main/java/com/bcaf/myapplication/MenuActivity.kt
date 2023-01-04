package com.bcaf.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActiviy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        menuCheckin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,CheckinActivity::class.java)
            startActivity(intent)
        })

        menuIjin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,IjinActivity::class.java)
            startActivity(intent)

        })
    }




}