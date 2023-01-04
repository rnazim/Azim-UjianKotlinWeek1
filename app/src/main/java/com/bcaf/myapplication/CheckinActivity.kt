package com.bcaf.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_checkin.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class CheckinActivity : AppCompatActivity() {

    var btnCheckin: Boolean = false;
    companion object {
        private val REQUEST_CODE_PERMISSION = 999
        private val CAMERA_REQUEST_CAPTURE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)

        imgCamera.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED && checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permission, REQUEST_CODE_PERMISSION)
                }else{
                    btnCheckin = true;
                    captureCamera()
                }
            }
        })

        btnLoginFoto.setOnClickListener(View.OnClickListener {
            if(btnCheckin==true){
                val intent = Intent(this,CheckinActivityBerhasil::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Login Foto Selfi Berhasil", Toast.LENGTH_LONG).show()
                btnCheckin=false;
            }

        })
    }

    fun captureCamera(){

        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivityForResult(takeCamera, CAMERA_REQUEST_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_PERMISSION -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    captureCamera()
                }else{
                    Toast.makeText(this, "Maaf Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun saveImage(bitmap: Bitmap){
        try {
            val tanggal = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

            val extraStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()

            val namaFile = extraStorage + "/BCAF_" + tanggal + ".png"
            var file : File? = null

            file = File(namaFile)
            file.createNewFile()

            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0,bos)
            val bitmapData = bos.toByteArray()

            val fos = FileOutputStream(file)

            fos.write(bitmapData)
            fos.flush()
            fos.close()
        } catch (e:java.lang.Exception){
            e.printStackTrace()
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST_CAPTURE && resultCode == RESULT_OK){
            val bitmapImage = data?.extras?.get("data") as Bitmap
            imgCamera.setImageBitmap(bitmapImage)
            saveImage(bitmapImage)
        }else{
            val intent = Intent(this,CheckinActivityGagal::class.java)
            startActivity(intent)
        }
    }


}