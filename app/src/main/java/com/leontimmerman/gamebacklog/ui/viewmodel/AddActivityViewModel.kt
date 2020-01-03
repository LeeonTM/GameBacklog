package com.leontimmerman.gamebacklog.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.leontimmerman.gamebacklog.database.GameRepository
import com.leontimmerman.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddActivityViewModel(application: Application): AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.IO)

    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

    fun insertGame(game: Game) {
        if (gameIsValid(game)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game)
                }
            }
            success.value = true
        }
    }

    private fun gameIsValid(game: Game): Boolean {
        return when {
            game == null -> {
                error.value = "Game must not be null"
                false
            }
            game.title.isBlank() -> {
                error.value = "Please fill in a title"
                false
            }
            game.platform.isBlank() -> {
                error.value = "Please fill in a platform"
                false
            }
            game.month.isBlank() -> {
                error.value = "Please fill in a month"
                false
            }
            // TODO: Date check
            else -> true
        }
    }
}