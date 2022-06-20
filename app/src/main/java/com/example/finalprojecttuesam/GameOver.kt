package com.example.finalprojecttuesam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.firestore.Query
import kotlinx.coroutines.delay
class GameOver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        var menu=findViewById<ImageView>(R.id.menu)
        var restart=findViewById<ImageView>(R.id.restart)
        var rank=findViewById<ImageView>(R.id.rank)
        menu.setOnClickListener{
            val intent = Intent(this, Start::class.java)
            startActivity(intent)
        }
        restart.setOnClickListener{
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }
        rank.setOnClickListener{
            val intent3 = Intent(this, Rank::class.java)
            startActivity(intent3)
        }
    }

}





