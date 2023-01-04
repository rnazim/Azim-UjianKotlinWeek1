package com.bcaf.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var container: LinearLayout
    val defaultUsername = "User"
    val defaultPassword = "123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener(View.OnClickListener {
            checkPassword(it)
        })

    }

    fun checkPassword(v: View) {
        if (txtInputUsername.text.contentEquals(defaultUsername) && txtInputPassword.text.contentEquals(
                defaultPassword
            )
        ) {
            Toast.makeText(applicationContext, "Berhasil login", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MenuActiviy::class.java)
//            intent.putExtra("username",txtInputUsername.text.toString())
//            intent.putExtra("password",txtInputPassword.text.toString())
            startActivity(intent)
        } else {
            Toast.makeText(
                applicationContext,
                "Username/Password tidak terdaftar",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}


