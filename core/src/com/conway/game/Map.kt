package com.conway.game

class Map(
    val columns: Int,
    val rows: Int,
    private val defaultValue: Boolean = false
) {

    private var array: Array<Array<Boolean>> = Array(columns) { Array(rows) { defaultValue } }

    operator fun get(x: Int, y: Int): Boolean = array[x][y]
    operator fun set(x: Int, y: Int, value: Boolean) {
        array[x][y] = value
    }

    internal fun isEmpty(x: Int, y: Int): Boolean {
        return !array[x][y]
    }

    private fun isOutOfBounds(
        x: Int,
        y: Int
    ): Boolean = (x < 0 || x >= columns) || (y < 0 || y >= rows)

    private inline val neighbourRange get() = (-1..1)

    fun neighbours(x: Int, y: Int): Int =
        neighbourRange.flatMap { dx ->
            neighbourRange.mapNotNull { dy ->
                val newX = x + dx
                val newY = y + dy

                // Filter out the cell itself and out of bounds
                if ((dx == 0 && dy == 0) || isOutOfBounds(newX, newY)) null else Pair(newX, newY)
            }
        }.count { (newX, newY) -> !isEmpty(newX, newY) }

    override fun toString(): String {

        val sb = StringBuilder()

        for (x in 0..< columns) {
            for (y in 0..< rows) {
                val pos = array[x][y]
                val symbol = if (pos) "■" else " "
                sb.append(symbol)
            }
            sb.append("\n")
        }
        return sb.toString()
    }
}