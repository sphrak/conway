package com.conway.game

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class GridRenderer(
    private val camera: Camera,
    private val blockSize: Float
) {

    private val renderer: ShapeRenderer = ShapeRenderer()

    fun render(map: Map) {
        renderer.begin(ShapeRenderer.ShapeType.Filled)
        renderer.projectionMatrix = camera.combined
        drawMap(map)
        renderer.end()
    }

    private fun drawMap(map: Map) {
        for (x in 0 ..< map.columns) {
            for (y in 0 ..< map.rows) {
                val cell = map[x, y]
                val neighbours = map.neighbours(x, y)

                renderer.color = if (cell) Color.WHITE else Color.BLACK /*when {
                    cell && neighbours == 2 -> Color.RED
                    cell && (neighbours == 3) -> Color.MAROON
                    !cell && (neighbours == 2) -> Color.FIREBRICK
                    !cell && (neighbours == 3) -> Color.TAN
                    cell -> Color.PURPLE
                    else -> Color.BLACK
                }*/
                renderer.rect(x * blockSize, y * blockSize, blockSize, blockSize)
            }
        }
    }

    fun dispose() {
        renderer.dispose()
    }

}