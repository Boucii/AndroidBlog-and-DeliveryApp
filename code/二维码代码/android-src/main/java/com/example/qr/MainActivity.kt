package com.example.qr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginbtn.setOnClickListener{
            val username=username.text.toString()
            val pwd=pwd.text.toString()


            login(username, pwd)
        }
        registerbtn.setOnClickListener{
            val username=username.text.toString()
            val pwd=pwd.text.toString()
            register(username,pwd)
        }
    }
    private fun register(username: String, pwd: String){
        if(username.isEmpty()){
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "请输入用户名",
                    Toast.LENGTH_LONG
                ).show()
            }
            return
        }
        if(pwd.length<5){
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "密码太短",
                    Toast.LENGTH_LONG
                ).show()
            }
            return
        }
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://192.168.137.1:8080/QRcode/RegisterServlet?username=" + username + "&password=" + pwd)
                    .build()
                val response = client.newCall(request).execute()
                val data = response.body?.string()
                if (data == "registersuccess!") {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "注册登陆成功",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    val Intent = Intent(this@MainActivity, Main2Activity::class.java)
                    startActivity(Intent)
                } else if (data == "duplicatedname") {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "用户名重复",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity,
                            "服务器内部错误",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private fun login(username: String, pwd: String){
        if(username.isEmpty()){
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "请输入用户名",
                    Toast.LENGTH_LONG
                ).show()
            }
            return
        }
        if(pwd.length<5){
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "请输入密码",
                    Toast.LENGTH_LONG
                ).show()
            }
            return
        }
        thread{
            try{
        val client=OkHttpClient()
        val request= Request.Builder().url("http://192.168.137.1:8080/QRcode/LoginServlet?username=" + username + "&password=" + pwd).build()
        val response= client.newCall(request).execute()
        val data= response.body?.string()
        if(data=="loginsuccess!"){
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "登陆成功",
                    Toast.LENGTH_LONG
                ).show()
            }
            val Intent= Intent(this@MainActivity, Main2Activity::class.java)
            startActivity(Intent)
        }else if(data=="adminlogin"){
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "管理员登陆成功",
                    Toast.LENGTH_LONG
                ).show()
            }
            val Intent= Intent(this@MainActivity, Main3Activity::class.java)
            startActivity(Intent)
        }
        else if(data=="namenotfound"){
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "用户名或密码错误",
                    Toast.LENGTH_LONG
                ).show()
            }
        }else{
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "服务器错误",
                    Toast.LENGTH_LONG
                ).show()
            }
        }}catch (e: java.net.ConnectException){
                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity,
                        "联网啊",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }

    }

}
