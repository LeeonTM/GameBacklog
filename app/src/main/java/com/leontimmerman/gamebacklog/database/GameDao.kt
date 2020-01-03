package com.leontimmerman.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leontimmerman.gamebacklog.model.Game

@Dao
interface GameDao {

    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM gamesTable LIMIT 1")
    fun getGames(): LiveData<List<Game>>

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM gamesTable")
    suspend fun deleteAllGames()
}