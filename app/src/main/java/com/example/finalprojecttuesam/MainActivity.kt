package com.example.finalprojecttuesam


import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.finalprojecttuesam.databinding.ActivityMainBinding
import kotlinx.coroutines.*
@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var job: Job
    lateinit var pic:ImageView
    lateinit var airplane:AirPlane
    var click:Boolean=false
    lateinit var surface:MySurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val img :ImageView=binding.myPic
        Glide.with(this)
            .load(R.drawable.mypic)
            .circleCrop()
            .override(800, 600)
            .into(img)

        surface=binding.mysv
        pic=binding.start
        pic.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                if(click){
                    click=false
                    pic.setImageResource(R.drawable.start)
                    job.cancel()
                }
                else{
                    click=true
                    pic.setImageResource(R.drawable.stop)
                    job=GlobalScope.launch(Dispatchers.Main) {
                        while (click) {
                            delay(25)
                            surface.airplane.update()
                            var canvas: Canvas = surface.surfaceHolder.lockCanvas()
                            surface.drawSomething(canvas)
                            surface.surfaceHolder.unlockCanvasAndPost(canvas)
                        }
                    }
                }
            }
        })
    }

}
