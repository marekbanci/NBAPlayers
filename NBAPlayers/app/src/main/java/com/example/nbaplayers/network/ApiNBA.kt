package com.example.nbaplayers.network

import com.example.nbaplayers.network.response.PlayersListResp
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNBA {

    @GET("players")
    suspend fun getPlayers(
        @Query("per_page") perPage: Int = 35,
    ) : PlayersListResp
}