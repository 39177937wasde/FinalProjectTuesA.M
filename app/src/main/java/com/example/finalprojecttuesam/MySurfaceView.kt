package com.example.finalprojecttuesam

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.SurfaceView


class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),SurfaceHolder.Callback{
    var surfaceHolder: SurfaceHolder = holder
    var BG:Bitmap
    var BGmoveX:Int = 0

    init{
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), com.example.finalprojecttuesam.R.drawable.background)
        surfaceHolder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {

        val canvas = surfaceHolder.lockCanvas()
        drawSomething(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)
    }
    fun drawSomething(canvas:Canvas) {

        var RectPic:Rect=Rect(0, 0, BG.getWidth(),BG.getHeight())
        var a: Rect = Rect(0, 0, width, height)
        canvas.drawBitmap(BG,RectPic, a,null)

        BGmoveX-=2
        var BGnewX:Int = BG.width + BGmoveX

        if (BGnewX <= 0) {
            BGmoveX = 0
            canvas.drawBitmap(BG,RectPic,a ,null)
        } else {
            a=Rect(BGmoveX,0,BGmoveX+width,height)
            canvas.drawBitmap(BG, RectPic, a, null)
            a=Rect(BGnewX, 0, BGnewX+width, height)
            canvas.drawBitmap(BG, RectPic, a, null)
        }

        val paint = Paint(1)
        paint.setColor(-16776961)
        paint.setTextSize(50.0f)
        canvas.drawText("射擊遊戲(作者：資管二A 陳柏睿)", 50.0f, 50.0f, paint)


    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

}