package com.example.finalprojecttuesam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class User : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        var button=findViewById<Button>(R.id.btnIn)
        var ed_name=findViewById<EditText>(R.id.edtName)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val name = ed_name.text.toString()
            if(name==""){
                Toast.makeText(this, "請輸入名稱", Toast.LENGTH_SHORT).show()
            }else if(name!="") {
                SharedData.userName=name
                startActivity(intent)
            }
        }
    }
}