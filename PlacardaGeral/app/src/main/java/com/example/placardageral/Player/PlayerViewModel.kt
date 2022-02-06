package com.example.placardageral.Player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class GAME {
    TRUCO,
    FOOTBALL,
    BASKET,
    NONE;
}

class PlayerViewModel: ViewModel() {
    var selectedGame: MutableLiveData<GAME> = MutableLiveData()

    init {
        startGame()
    }
    fun selectGame(game: GAME) {
        selectedGame.value = game
    }

    private fun startGame() {
        selectedGame.value = GAME.NONE
    }
}