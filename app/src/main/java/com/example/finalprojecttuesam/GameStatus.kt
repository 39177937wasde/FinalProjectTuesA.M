package com.example.finalprojecttuesam

import android.app.SharedElementCallback
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.delay
import java.util.*

class GameStatus(context: Context) {
    val res = context.resources  //讀取資源
    var PlayerY:Int = (res.displayMetrics.heightPixels*3.7/5).toInt()
    var width:Int=res.displayMetrics.widthPixels
    var boxHeight=(res.displayMetrics.heightPixels*3.7/5).toInt()
    val str:String = "SCORE"
    val scorePaint = Paint()
    var score:Int = 0
    var w:Int = 0
    var h:Int = 0
    var boxy:Float=0f
    var boxX:Float=0f
    var boxmovingX:Float=-10f
    var boxnewX=0f
    var boxnewX2=0f
    var FinalScore=0L
    lateinit var main:MainActivity
    var img: Bitmap
    var img2: Bitmap
    var flag:Boolean=false
    init {
        SharedData.ground=(res.displayMetrics.heightPixels*3.7/5).toInt()
        img = BitmapFactory.decodeResource(res, R.drawable.run00)
        img2=BitmapFactory.decodeResource(res, R.drawable.box02)
        w =img.width/2
        h =img.height/2
        boxX=width-img2.width.toFloat()
        boxy=(boxHeight+25).toFloat()
        boxnewX=width-img2.width.toFloat()
        boxnewX2=width-img2.width.toFloat()
        scorePaint.color = Color.BLACK
        scorePaint.textSize = 70F
        scorePaint.typeface = Typeface.DEFAULT_BOLD
        scorePaint.isAntiAlias = true
    }
    var walk:Int=0
    fun draw(canvas: Canvas) {

        boxnewX+=boxmovingX
        if(img.width>=boxnewX&&PlayerY>=boxHeight-img2.height||(img.width>=boxnewX2)&&(PlayerY.toFloat()>=boxHeight-img2.height)){
            Log.d(this.toString(),"img.width:${img.width},$boxnewX PlayerY.toFloat():${PlayerY.toFloat()},${boxHeight-img2.height}",null)
            SharedData.die=true
            //val i= Intent(Context!=this, GameOver::class.java)
        }/*else if((img.width>=boxnewX2)&&(PlayerY.toFloat()>=boxHeight)){

            SharedData.die=true
        }*/

        if(boxnewX==(width/2).toFloat()-5f){
            flag=true
            canvas.drawBitmap(img2,boxnewX2,boxy,null)
            Log.d(this.toString(),"boxnewX2:$boxnewX2",null)
        }
        if(flag){
            boxnewX2+=boxmovingX
            canvas.drawBitmap(img2,boxnewX2,boxy,null)
        }
        if(boxnewX2<=0){
            Log.d(this.toString(),"11111111111111111",null)
            boxnewX2=width-img2.width.toFloat()
            //canvas.drawBitmap(img2,boxnewX2,boxy,null)
            flag=false
        }
        canvas.drawBitmap(img, 10f,PlayerY.toFloat(), null)
        //Log.d(this.toString(),"SharedData.ground:${SharedData.ground} img.width:${img.width}",null)
        //Log.d(this.toString(),"boxnewX:${boxnewX} boxHeight:${img.width<=boxnewX&&SharedData.ground<=boxHeight}",null)
        canvas.drawBitmap(img2,boxnewX,boxy,null)
        if(boxnewX<=0){
            boxnewX=width-img2.width.toFloat()
            canvas.drawBitmap(img2,boxnewX,boxy,null)
        }
        var time2=System.currentTimeMillis()
        FinalScore=(time2-SharedData.maintime)/1000
        SharedData.finalscore=FinalScore.toInt()
        canvas.drawText("Score : $FinalScore", 50F,70F,scorePaint )
    }
    fun update() {
        when(walk){
            1->{img = BitmapFactory.decodeResource(res, R.drawable.run00) }
            2->{img = BitmapFactory.decodeResource(res, R.drawable.run01)}
            3->{img = BitmapFactory.decodeResource(res, R.drawable.run02)}
            4->{img = BitmapFactory.decodeResource(res, R.drawable.run03)}
            5->{img = BitmapFactory.decodeResource(res, R.drawable.run04)}
            6->{img = BitmapFactory.decodeResource(res, R.drawable.run05)}
        }
        walk++
        if(walk>6) {
            walk = 0
        }
    }
    fun moving(){
        if(SharedData.flag==1){
            if(PlayerY>500) {
                PlayerY += SharedData.up
            }
            else{
                SharedData.flag=0
            }
        }
        else if(SharedData.flag==0){
            if(PlayerY<SharedData.ground) {
                PlayerY += SharedData.down
            }
            else{
                SharedData.flag=3
            }
        }
    }

}
