package com.example.qr

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.qr.util.Constant
import com.google.gson.Gson
import com.google.zxing.activity.CaptureActivity
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONArray
import org.json.JSONObject


class Main2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        scanbtn.setOnClickListener{
            startQrCode()
        }

    }
    private fun startQrCode() {
        // 申请相机权限
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            // 申请权限
            ActivityCompat.requestPermissions(
                this@Main2Activity,
                arrayOf(Manifest.permission.CAMERA),
                Constant.REQ_PERM_CAMERA
            )
            return
        }
        // 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
        /*
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            // 申请权限
            ActivityCompat.requestPermissions(
                this@Main2Activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                Constant.REQ_PERM_EXTERNAL_STORAGE
            )
            return
        }*/
        // 二维码扫码
        val intent = Intent(this@Main2Activity, CaptureActivity::class.java)
        startActivityForResult(intent, Constant.REQ_QR_CODE)
    }

    override protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            val bundle = data?.extras
            val scanResult = bundle!!.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN)
            val textView: TextView = findViewById(R.id.scanResult) as TextView
            //将扫描出的信息显示出来
            //val gson= Gson()
            //val json=gson.toJson(scanResult);
            val json= JSONObject(scanResult)
            val res="发件人:"+json.getString("sendername")+"\n收件人:"+json.getString("receivername")+"\n地址:"+json.get("Address")+"\n电话:"+json.get("receievertel")
            textView.text=res
        }
    }

}
