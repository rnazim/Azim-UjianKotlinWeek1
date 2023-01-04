package com.bcaf.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_checkin_berhasil.*
import kotlinx.android.synthetic.main.activity_checkin_gagal.*

class CheckinActivityGagal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin_gagal)

        btnRescan.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,CheckinActivity::class.java)
            startActivity(intent)
        })
    }


}