package br.com.casadocodigo.impossible

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class ImpossibleSurfaceView(context: Context) : SurfaceView(context), Runnable {

    private val surfaceHolder: SurfaceHolder = holder
    private val paint = Paint()

    private var running = false
    private lateinit var renderThread: Thread

    fun onResume() {
        running = true
        renderThread = Thread(this)
        renderThread.run()
    }

    override fun run() {
        while (running) {
            if (!surfaceHolder.surface.isValid) continue

            val canvas = surfaceHolder.lockCanvas()
            drawPlayer(canvas)
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    private fun drawPlayer(canvas: Canvas) {
        paint.color = Color.GREEN
        canvas.drawCircle(100f, 100f, 100f, paint)
    }
}