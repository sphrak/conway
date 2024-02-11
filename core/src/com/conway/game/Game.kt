package com.conway.game

import kotlin.random.Random

class Game(
    private var current: Map,
    private var next: Map = Map(current.columns, current.rows),
    seed: Long = (System.currentTimeMillis() / 1000L)
) {

    val map: Map get() = current

    init { randomize(seed) }

    private fun randomize(seed: Long) {
        val rand = Random(seed)
        for (x in 0..<current.columns) {
            for (y in 0..<current.rows) {
                if (rand.nextBoolean()) {
                    current[x, y] = true
                }
            }
        }
    }

    fun tick() {
        for (x in 0 until current.columns) {
            for (y in 0 until current.rows) {
                if (current.isEmpty(x, y)) {
                    next[x, y] = current.neighbours(x, y) == 3
                } else {
                    val neighbours = current.neighbours(x, y)
                    next[x, y] = (neighbours == 2 || neighbours == 3)
                }
            }
        }

        val prev = current
        current = next
        this.next = prev
    }
}
