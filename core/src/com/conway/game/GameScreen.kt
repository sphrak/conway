package com.conway.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import java.util.concurrent.Executors


class GameScreen : Screen {

    private val w = Gdx.graphics.width.toFloat()
    private val h = Gdx.graphics.height.toFloat()
    private val game = Game(current = Map(160 * 2, 120 * 2))

    private val viewportWidth = 30f
    private val viewportHeight = 30f * (h / w)

    private val camera: OrthographicCamera = OrthographicCamera(
        viewportWidth,
        viewportHeight
    )

    private val renderer: GridRenderer = GridRenderer(
        camera = camera,
        blockSize = calculateCellSize()
    )

    private val executor = Executors.newSingleThreadExecutor()

    private fun calculateCellSize(): Float {
        // Calculate the aspect ratios
        val mapAspectRatio = game.map.columns.toFloat() / game.map.rows.toFloat()
        val viewportAspectRatio = w / h

        // Determine if width or height is the limiting factor
        return if (mapAspectRatio >= viewportAspectRatio) {
            // Map is wider relative to its height than the viewport is relative to its height
            // Base cell size on the viewport width
            viewportWidth / game.map.columns
        } else {
            // Map is taller relative to its width than the viewport is relative to its width
            // Base cell size on the viewport height
            (viewportWidth * (h / w)) / game.map.rows
        }
    }

    override fun show() {
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f)
        camera.update()

        executor.execute {
            while (true) {
                Thread.sleep(60)
                game.tick()
            }
        }
    }


    override fun render(p0: Float) {
        renderer.render(game.map)
    }

    override fun resize(p0: Int, p1: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
        renderer.dispose()
    }
}