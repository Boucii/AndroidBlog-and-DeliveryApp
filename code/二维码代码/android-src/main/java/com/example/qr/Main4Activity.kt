package com.example.qr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qr.R
import kotlinx.android.synthetic.main.activity_main4.*
import kotlinx.android.synthetic.main.activity_main4.fab
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class Main4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        fab.setOnClickListener{
            val Intent= Intent(this@Main4Activity, Main3Activity::class.java)
            startActivity(Intent)
        }
        updatebtn.setOnClickListener{
            updatelevel()
        }
    }
    fun updatelevel() {
         val box1:Boolean =checkBox.isChecked
         val box2:Boolean=checkBox2.isChecked
         val box3:Boolean=checkBox3.isChecked
         val box4:Boolean=checkBox4.isChecked
         var authtobe:Int=0
        if (box1){
            authtobe +=1
        }
        if(box2){
            authtobe +=2
        }
        if(box3){
            authtobe +=4
        }
        if(box4){
            authtobe +=8
        }
        thread{
            try{
                val client= OkHttpClient()
                val request= Request.Builder().url("http://192.168.137.1:8080/QRcode/UpdateAuthServlet?authtobe=" + authtobe).build()
                val response= client.newCall(request).execute()
                val data= response.body?.string()
                if(data=="updatesuccess"){
                    runOnUiThread {
                        Toast.makeText(
                            this@Main4Activity,
                            "修改成功",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
    }catch(e: Exception){
            e.printStackTrace()
            }
        }
    }
}
