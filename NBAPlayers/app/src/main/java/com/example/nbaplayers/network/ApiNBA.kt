package com.example.nbaplayers.network

import com.example.nbaplayers.model.Player
import com.example.nbaplayers.network.response.PlayersListResp
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiNBA {

    @GET("players")
    suspend fun getPlayers(
        @Query("per_page") perPage: Int = 35,
        @Query("cursor") cursor: Int = 0
    ) : PlayersListResp

    @GET("players/{id}")
    suspend fun getPlayerById(@Path("id") id: Int): Player
}