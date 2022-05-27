package com.example.finalprojecttuesam

import android.content.Context
import android.graphics.*
import android.media.MediaPlayer
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.*

class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),SurfaceHolder.Callback, GestureDetector.OnGestureListener{
    var surfaceHolder: SurfaceHolder = holder
    var BG:Bitmap
    var BGmoveX:Int = 0
    var airplane:AirPlane
    lateinit var shoot:MediaPlayer
    var gDetector:GestureDetector
    init{
        airplane=AirPlane(context!!)
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(),R.drawable.background)
        surfaceHolder.addCallback(this)
        gDetector= GestureDetector(context,this)
        shoot = MediaPlayer.create(context, R.raw.shoot)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {

        var canvas:Canvas = surfaceHolder.lockCanvas()
        drawSomething(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }
    fun drawSomething(canvas:Canvas) {
        var RectPic:Rect=Rect(0, 0, BG.getWidth(),BG.getHeight())
        var wid:Int=width
        var hei:Int=height
        var a: Rect = Rect(0, 0, wid, hei)
        BGmoveX-=2
        var BGnewX:Int = wid + BGmoveX

        if (BGnewX <= 0) {
            BGmoveX = 0
            canvas.drawBitmap(BG,RectPic,a ,null)
        } else {
            a=Rect(BGmoveX,0,BGmoveX+wid,hei)
            canvas.drawBitmap(BG, RectPic, a, null)
            a=Rect(BGnewX, 0, BGnewX+wid,hei)
            canvas.drawBitmap(BG, RectPic, a, null)
        }

        val paint = Paint(1)
        paint.setColor(-16776961)
        paint.setTextSize(50.0f)
        canvas.drawText("射擊遊戲(作者：資管二A 陳柏睿)", 50.0f, 50.0f, paint)
        airplane.draw(canvas)

    }


    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
        if (e!!.x >= 0 && e!!.x <= airplane.w && e!!.y >= airplane.AirpalneY && e!!.y <= airplane.AirpalneY + airplane.w) {
            airplane.shoot=1
            shoot = MediaPlayer.create(context, R.raw.shoot)
            shoot.start()

        }
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        airplane.AirpalneY=e2!!.y.toInt()-airplane.h/2
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }
}