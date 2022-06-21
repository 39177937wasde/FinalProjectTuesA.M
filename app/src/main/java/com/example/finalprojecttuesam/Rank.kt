package com.example.finalprojecttuesam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source

var db = FirebaseFirestore.getInstance()
var user: MutableMap<String, Any> = HashMap()
class Rank : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)
        var prew =findViewById<ImageView>(R.id.prew)
        prew.setOnClickListener{
            val intent = Intent(this, GameOver::class.java)
            startActivity(intent)
        }

        read()
    }
    fun read(){
        var txv=findViewById<TextView>(R.id.txv)

        db.collection("Users")
            .orderBy("score", Query.Direction.DESCENDING)
            .limit(5)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var msg: String = ""
                    for (document in task.result!!) {
                        msg += "名字:"+document.id+" score：" + document.data["score"].toString()+"\n"
                    }
                    if (msg != "") {
                        txv.text = msg
                    } else {
                        txv.text = "查無資料"
                    }
                }
            }
    }
}