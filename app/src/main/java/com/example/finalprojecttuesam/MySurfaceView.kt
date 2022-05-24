package com.example.finalprojecttuesam

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.jvm.internal.Intrinsics


class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),SurfaceHolder.Callback{
    var surfaceHolder: SurfaceHolder = holder
    var BG:Bitmap




    init{
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), com.example.finalprojecttuesam.R.drawable.background)
        surfaceHolder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {

        val canvas = surfaceHolder.lockCanvas()
        Intrinsics.checkNotNullExpressionValue(canvas, "surfaceHolder.lockCanvas()")
        drawSomething(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)
    }
    fun drawSomething(canvas:Canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas")
        canvas.drawBitmap(BG,Rect(0, 0, BG.getWidth(),BG.getHeight()), Rect(0, 0, width, height)
            , null as Paint?
        )
        val paint = Paint(1)
        paint.setColor(-16776961)
        paint.setTextSize(50.0f)
        canvas.drawText("射擊遊戲(作者：資管二A 陳柏睿)", 50.0f, 50.0f, paint)
        /*val scale =BG.height.toFloat() / height.toFloat()
        val newWidth = Math.round(BG.width / scale)
        val newHeight = Math.round(BG.height / scale)
        var scaled:Bitmap = Bitmap.createScaledBitmap(BG, newWidth, newHeight, true)*/
       // canvas.drawBitmap(scaled, 0f, 0f, null)
    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

}