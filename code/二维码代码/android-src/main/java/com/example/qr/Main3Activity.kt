package com.example.qr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qr.R
import kotlinx.android.synthetic.main.activity_main3.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        fab.setOnClickListener{
            val Intent= Intent(this@Main3Activity, Main4Activity::class.java)
            startActivity(Intent)
        }
        sendpackage.setOnClickListener{
            createpackage();
        }
    }
    fun createpackage() {
        val sendername = sendername.text.toString()
        val receivername = receivername.text.toString()
        val tel = tel.text.toString()
        val addr=addr.text.toString()
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://192.168.137.1:8080/QRcode/NewPackageServlet?sendername="+sendername+"&receivername="+receivername+"&tel="+tel+"&addr="+addr).build()
                val response = client.newCall(request).execute()
                val data = response.body?.string()
                if (data == "createsuccess") {
                    runOnUiThread {
                        Toast.makeText(
                            this@Main3Activity,
                            "包裹生成成功",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
