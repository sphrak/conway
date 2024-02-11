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

    //        if (xPos < 0 || xPos >= columns) {
    //            return true
    //        }
    //        if (yPos < 0 || yPos >= rows) {
    //            return true
    //        }
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

//    fun neighbours(x: Int, y: Int): Int {
//        var neighbors = 0
//        for (i in -1..1) {
//            for (j in -1..1) {
//                val x1 = x + i
//                val y1 = y + j
//
//                // Skip position of the tested cell itself
//                if (x1 == x && y1 == y) {
//                    continue
//                }
//
//                // Skip out of bounds
//                if (isOutOfBounds(x1, y1)) {
//                    continue
//                }
//
//                // Increase neighbor count if not empty
//                if (!isEmpty(x1, y1)) {
//                    neighbors++
//                }
//            }
//        }
//        return neighbors
//    }
}