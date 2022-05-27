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
    lateinit var image: Bitmap
    lateinit var SrcRect: Rect
    lateinit var DestRect: Rect
    init {
        image = BitmapFactory.decodeResource(res, R.drawable.fly1)
        w = image.width/4
        h = image.height/4
        SrcRect = Rect(0, 0, image.width, image.height) //裁切
    }
    var count: Int = 1
    fun update() {
        count++
        if (count > 2) {
            count = 1
        }
    }
        fun draw(canvas: Canvas) {
            update()
            when (count) {
                1 -> image = BitmapFactory.decodeResource(res, R.drawable.fly1)
                2 -> image = BitmapFactory.decodeResource(res, R.drawable.fly2)
            }
            DestRect = Rect(0, AirpalneY, 0 + w, AirpalneY + h)
            canvas.drawBitmap(image, SrcRect, DestRect, null)
        }
}
