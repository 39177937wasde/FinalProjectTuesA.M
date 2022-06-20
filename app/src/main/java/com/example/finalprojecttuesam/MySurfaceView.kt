package com.example.finalprojecttuesam

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.*

class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),SurfaceHolder.Callback, GestureDetector.OnGestureListener{
    var surfaceHolder: SurfaceHolder = holder
    var BG:Bitmap
    var img2:Bitmap
    var BGmoveX:Int = 0
    var gameStatus:GameStatus
    var boxw:Int=0
    var boxh=0
    var boxy=0
    var boxX=0.0
    var gDetector:GestureDetector
    lateinit var canvas:Canvas
    init{
        gameStatus=GameStatus(context!!)
        surfaceHolder = holder
        BG = BitmapFactory.decodeResource(resources,R.drawable.bg)
        img2=BitmapFactory.decodeResource(resources,R.drawable.box01)
        surfaceHolder.addCallback(this)
        gDetector= GestureDetector(context,this)
        boxh=img2.height/2
        boxw=img2.width/2
        boxy=gameStatus.PlayerY+25
        boxX=(width-boxw).toDouble()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
            canvas = surfaceHolder.lockCanvas()
            drawSomething(canvas)
            surfaceHolder.unlockCanvasAndPost(canvas)

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d(this.toString(),"有跑到",null)
    }
    fun drawSomething(canvas:Canvas) {
        var RectPic:Rect=Rect(0, 0, BG.getWidth(),BG.getHeight())


       var boxSrcRect=Rect(0,0,img2.width,img2.height)

       // var boxDestRect = Rect(boxX.toInt(),  boxy, width,  airplane.AirplaneY+boxh+25 )
        var wid:Int=width
        var hei:Int=height
        var a: Rect = Rect(0, 0, wid, hei)
        BGmoveX-=2
        var BGnewX:Int = wid + BGmoveX

        if (BGnewX <= 0) {
            BGmoveX = 0
            canvas.drawBitmap(BG,RectPic,a ,null)
            //canvas.drawBitmap(img2,boxDestRect,boxSrcRect ,null)
        } else {
            a=Rect(BGmoveX,0,BGmoveX+wid,hei)
            //canvas.drawBitmap(img2,boxSrcRect, a,null)
            canvas.drawBitmap(BG, RectPic, a, null)
            a=Rect(BGnewX, 0, BGnewX+wid,hei)
            //canvas.drawBitmap(img2,boxSrcRect, a,null)
            canvas.drawBitmap(BG, RectPic, a, null)
        }
        gameStatus.draw(canvas)

    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        SharedData.flag=1

        return true
    }

    override fun onShowPress(e: MotionEvent?) {
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
        //airplane.AirpalneY=e2!!.y.toInt()-airplane.h/2
        return true
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }
    fun relase() {
        BG.recycle()
    }

}