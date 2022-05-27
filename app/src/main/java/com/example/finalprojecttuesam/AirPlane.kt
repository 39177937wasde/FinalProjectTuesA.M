package com.example.finalprojecttuesam

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.provider.SyncStateContract.Helpers.update

class AirPlane(context: Context) {
    val res = context.resources  //讀取資源
    var AirpalneY:Int = res.displayMetrics.heightPixels/2
    var w:Int = 0
    var h:Int = 0
    lateinit var img: Bitmap
    lateinit var SrcRect: Rect
    lateinit var DestRect: Rect
    init {
        img = BitmapFactory.decodeResource(res, R.drawable.fly1)
        w = img.width/4
        h = img.height/4
        SrcRect = Rect(0, 0, img.width, img.height) //裁切
        AirpalneY-= h/2
    }
    var count: Int = 1
    var shoot:Int=0
    fun draw(canvas: Canvas) {
        DestRect = Rect(0, AirpalneY, 0 + w, AirpalneY + h)
        canvas.drawBitmap(img, SrcRect, DestRect, null)
    }
    fun update() {
        if(shoot==0){
            if(count==1){
                count=2
                img=BitmapFactory.decodeResource(res, R.drawable.fly2)
            }
            else
                {
                    count=1
                    img=BitmapFactory.decodeResource(res, R.drawable.fly1)
                }
            }
        else
        {
            when(shoot){
                1 -> img = BitmapFactory.decodeResource(res, R.drawable.shoot1)
                2 -> img = BitmapFactory.decodeResource(res, R.drawable.shoot2)
                3 -> img = BitmapFactory.decodeResource(res, R.drawable.shoot3)
                4 -> img = BitmapFactory.decodeResource(res, R.drawable.shoot4)
                5 -> img = BitmapFactory.decodeResource(res, R.drawable.shoot5)
            }
            shoot++
            if(shoot>5) {
                shoot = 0
            }
        }
    }
        fun shooting(shoot:Int){

        }

}
