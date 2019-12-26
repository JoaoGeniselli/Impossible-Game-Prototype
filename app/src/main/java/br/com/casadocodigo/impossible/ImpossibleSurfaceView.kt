package br.com.casadocodigo.impossible

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class ImpossibleSurfaceView(context: Context) : SurfaceView(context), Runnable {

    private val surfaceHolder: SurfaceHolder = holder
    private val paint = Paint()

    private var playerX = 300f
    private var playerY = 300f
    private var playerRadius = 50f

    private var enemyX = 50f
    private var enemyY = 50f
    private var enemyRadius = 50f

    private var score = 0

    private var distance: Double = 0.0
    private var gameOver = false

    private var running = false
    private lateinit var renderThread: Thread

    fun initGame() {
        enemyX = 0f
        enemyY = 0f
        enemyRadius = 0f
        playerY = 300f
        playerX = 300f
        playerRadius = 50f
        score = 0
        gameOver = false
    }

    fun onResume() {
        running = true
        renderThread = Thread(this)
        renderThread.start()
    }

    override fun run() {
        while (running) {
            if (!surfaceHolder.surface.isValid) continue
            val canvas = surfaceHolder.lockCanvas()
            canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.sky), 0f, 0f, null)
            drawPlayer(canvas)
            drawEnemy(canvas)
            checkCollision(canvas)
            if (gameOver) stopGame(canvas)
            drawScore(canvas)
            drawButtons(canvas)
            surfaceHolder.unlockCanvasAndPost(canvas)

            if (gameOver) break
        }
    }

    private fun drawPlayer(canvas: Canvas) {
        canvas.drawBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.nave),
            playerX - 50f,
            playerY - 50f,
            null
        )
    }

    private fun drawEnemy(canvas: Canvas) {
        paint.color = Color.RED
        enemyRadius++
        canvas.drawCircle(enemyX, enemyY, enemyRadius, paint)
    }

    fun movePlayerDown(pixels: Float) {
        playerY += pixels
    }

    private fun checkCollision(canvas: Canvas) {
        val yAxis = Math.pow(playerY.toDouble() - enemyY.toDouble(), 2.0)
        val xAxis = Math.pow(playerX.toDouble() - enemyX.toDouble(), 2.0)
        distance = Math.sqrt(yAxis + xAxis)

        if (distance <= playerRadius + enemyRadius) {
            gameOver = true
        }
    }

    private fun stopGame(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        paint.color = Color.BLUE
        paint.textSize = 100f
        canvas.drawText("GAME	OVER!", 50f, 150f, paint)
    }

    fun addScore(points: Int) {
        score += points
    }

    private fun drawScore(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        paint.textSize = 50f
        canvas.drawText(score.toString(), 50f, 200f, paint)
    }

    private fun drawButtons(canvas: Canvas) {
        //	Restart
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        paint.textSize = 30f
        canvas.drawText("Restart", 50f, 300f, paint)
        //	Exit
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        paint.textSize = 30f
        canvas.drawText("Exit", 50f, 500f, paint)
    }
}