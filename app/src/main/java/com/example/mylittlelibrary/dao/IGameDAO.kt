package com.example.mylittlelibrary.dao

import com.example.mylittlelibrary.data.Game
import retrofit2.Call
import retrofit2.http.GET

interface IGameDAO {

    @GET("/krishemenway/games-json/blob/master/games.json")
    fun getAllGames() : Call<ArrayList<Game>>

}