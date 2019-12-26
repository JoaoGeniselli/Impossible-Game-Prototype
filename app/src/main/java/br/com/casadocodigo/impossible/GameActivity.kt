package br.com.casadocodigo.impossible

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View

class GameActivity : AppCompatActivity(), View.OnTouchListener {

    private lateinit var view: ImpossibleSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ImpossibleSurfaceView(this)
        view.setOnTouchListener(this)
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        view.onResume()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let {
            if (event.x < 100 && event.y > 290 && event.y < 310) {
                view.initGame()
            }

            if (event.x < 100 && event.y > 490 && event.y < 510) {
                System.exit(0)
            }
        }

        with(view) {
            movePlayerDown(10f)
            addScore(100)
        }
        return true
    }
}
