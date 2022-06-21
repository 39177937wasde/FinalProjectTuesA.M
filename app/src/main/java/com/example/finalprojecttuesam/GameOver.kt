package com.example.finalprojecttuesam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.delay
class GameOver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        var db = FirebaseFirestore.getInstance()
        var user: MutableMap<String, Any> = HashMap()
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
        var docref=db.collection("Users")
            .document("${SharedData.userName}")
        docref.get().addOnCompleteListener(){task->
            if (task.isSuccessful) {
                val document = task.result
                if(document != null) {
                    if(document.exists()){
                        //Log.d("TAG", "Document already exists ${docref.get(user.getValue(SharedData.userName) as Source)}")
                        user["score"] =SharedData.finalscore
                        docref.update(user)
                    }else{
                        Log.d("TAG", "Document doesn't exist.")
                        user["score"] =SharedData.finalscore
                        docref.set(user)

                    }
                }else{
                    Log.d("TAG", "Error: ", task.exception)
                }
            }
        }
    }

}





