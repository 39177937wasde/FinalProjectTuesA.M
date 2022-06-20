package com.example.finalprojecttuesam

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        var pic =findViewById<ImageView>(R.id.start)
        pic.setOnClickListener{
            val intent = Intent(this, User::class.java)
            startActivity(intent)
        }
    }
}