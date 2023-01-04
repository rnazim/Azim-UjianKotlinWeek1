package com.bcaf.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_checkin.*
import kotlinx.android.synthetic.main.activity_ijin.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.String.format
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

class IjinActivity : AppCompatActivity() {

    var btnLampiran1: Boolean = false;
    var btnLampiran2: Boolean = false;
    var btnLampiran3: Boolean = false;

    var picktglDari: Boolean = false;
    var picktglSampai: Boolean = false;

    companion object {
        private val REQUEST_CODE_PERMISSION = 99
        private val CAMERA_REQUEST_CAPTURE = 100
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ijin)


        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinnerKategori, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerkategori.adapter = adapter

        tglDari.setOnClickListener(View.OnClickListener {
            picktglDari = true;
            pickDate()
        })
        tglSampai.setOnClickListener(View.OnClickListener {
            picktglSampai = true;
            pickDate()
        })

        imgLampiran1.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED && checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    val permission = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permission, REQUEST_CODE_PERMISSION)
                } else {
                    btnLampiran1 = true;
                    captureCamera()
                }
            }
        })

        imgLampiran2.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED && checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    val permission = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permission, REQUEST_CODE_PERMISSION)
                } else {
                    btnLampiran2 = true;
                    captureCamera()
                }
            }
        })

        imgLampiran3.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED && checkSelfPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    val permission = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permission, REQUEST_CODE_PERMISSION)
                } else {
                    btnLampiran3 = true;
                    captureCamera()
                }
            }
        })

    }

    fun pickDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDateSet(
                view: DatePicker, year: Int, monthofYear: Int, dayofMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthofYear)
                c.set(Calendar.DAY_OF_MONTH, dayofMonth)

                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                if (picktglDari == true) {
                    editTextTglMulai.setText(sdf.format(c.getTime()))
                    picktglDari = false;
                } else if (picktglSampai == true) {
                    editTextTglSampai.setText(sdf.format(c.getTime()))
                    picktglSampai = false;
                }

            }
        }

        DatePickerDialog(
            this,
            dateSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun captureCamera() {

        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivityForResult(takeCamera, CAMERA_REQUEST_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    captureCamera()
                } else {
                    Toast.makeText(this, "Maaf Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun saveImage(bitmap: Bitmap) {
        try {
            val tanggal = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

            val extraStorage =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()

            val namaFile = extraStorage + "/BCAF_" + tanggal + ".png"
            var file: File? = null

            file = File(namaFile)
            file.createNewFile()

            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            val bitmapData = bos.toByteArray()

            val fos = FileOutputStream(file)

            fos.write(bitmapData)
            fos.flush()
            fos.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CAPTURE && resultCode == RESULT_OK) {
            val bitmapImage = data?.extras?.get("data") as Bitmap
            if (btnLampiran1 == true) {
                imgLampiran1.setImageBitmap(bitmapImage)
                saveImage(bitmapImage)
                btnLampiran1 = false;
            } else if (btnLampiran2 == true) {
                imgLampiran2.setImageBitmap(bitmapImage)
                saveImage(bitmapImage)
                btnLampiran2 = false;
            } else if (btnLampiran3 == true)
                imgLampiran3.setImageBitmap(bitmapImage)
            saveImage(bitmapImage)
            btnLampiran3 = false;

        }
    }
}
