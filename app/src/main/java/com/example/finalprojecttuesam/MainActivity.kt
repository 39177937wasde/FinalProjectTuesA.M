package com.example.finalprojecttuesam


import android.content.Intent
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.finalprojecttuesam.databinding.ActivityMainBinding
import kotlinx.coroutines.*
class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    /*fun MainScope(): CoroutineScope =
        ContextScope(SupervisorJob() + Dispatchers.Main)*/

    lateinit var binding: ActivityMainBinding
    lateinit var job: Job
    lateinit var job2: Job
    lateinit var pic:ImageView
    lateinit var gameStatus:GameStatus
    var write:GameOver= GameOver()
    var click:Boolean=false
    lateinit var surface:MySurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        SharedData.maintime=System.currentTimeMillis()
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        surface=binding.mysv
        SharedData.die=false
        //pic.setImageResource(R.drawable.stop)
        GlobalScope.launch(Dispatchers.Main) {
            while (!SharedData.die) {
                try {
                    delay(1)
                    var canvas: Canvas = surface.surfaceHolder.lockCanvas()
                    surface.drawSomething(canvas)
                    surface.surfaceHolder.unlockCanvasAndPost(canvas)
                    if(SharedData.die){
                        delay(10L)
                        var i =Intent(this@MainActivity,GameOver::class.java)
                        startActivity(i)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        }
        GlobalScope.launch {
            while(!SharedData.die){
                surface.gameStatus.update()
                delay(90)
            }
        }
        GlobalScope.launch {
            while(!SharedData.die){
                surface.gameStatus.moving()
                delay(20)
            }
        }

    }

}
/*click=false
        //pic.setImageResource(R.drawable.start)
        Log.d(this.toString(),"SharedData.die:${SharedData.die}",null)
        val i= Intent(this, GameOver::class.java)
        job.cancel()
        job2.cancel()





























        Log.d(this.toString(),"newX:${SharedData.die}",null)
                    click=true
                    //pic.setImageResource(R.drawable.stop)
                    GlobalScope.launch(Dispatchers.Main) {
                        while (true) {
                            try {
                                delay(1)
                                if(SharedData.die){break}
                                var canvas: Canvas = surface.surfaceHolder.lockCanvas()
                                surface.drawSomething(canvas)
                                surface.surfaceHolder.unlockCanvasAndPost(canvas)
                                Log.d(this.toString(),"newX:${SharedData.die}",null)

                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            finally {

                            }

                        }

                    }
                GlobalScope.launch {
                        while(!SharedData.die){
                            surface.airplane.update()
                            Log.d(this.toString(),"13:${SharedData.die}",null)
                            delay(90)
                        }
                    }
        if(SharedData.die){
            val i= Intent(this, GameOver::class.java)
            startActivity(i)
        }
*/