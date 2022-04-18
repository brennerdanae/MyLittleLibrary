package com.example.mylittlelibrary.service

import com.example.mylittlelibrary.RetrofitClientInstance
import com.example.mylittlelibrary.dao.IGameDAO
import com.example.mylittlelibrary.data.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class GameService {

    suspend fun fetchGames(): List<Game>? {
        return withContext(Dispatchers.IO) {
            val service = RetrofitClientInstance.retrofitInstance?.create(IGameDAO::class.java)
            val games = async { service?.getAllGames() }
            var result = games.await()?.awaitResponse()?.body()
            return@withContext result
        }

    }
}