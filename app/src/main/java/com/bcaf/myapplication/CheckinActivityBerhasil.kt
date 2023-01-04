package com.bcaf.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_checkin.*
import kotlinx.android.synthetic.main.activity_checkin_berhasil.*

class CheckinActivityBerhasil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin_berhasil)

        btnBerhasil.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MenuActiviy::class.java)
            startActivity(intent)
        })
    }
}