package br.com.casadocodigo.impossible

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

class GameActivity : AppCompatActivity() {

    private lateinit var view: ImpossibleSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ImpossibleSurfaceView(this)
        setContentView(view)
        setAsFullscreen()
    }

    private fun setAsFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun onResume() {
        super.onResume()
        view.onResume()
    }
}
