package com.conway.game

import com.badlogic.gdx.Game

class Conway : Game() {

    override fun create() {
        setScreen(GameScreen())
    }

}
