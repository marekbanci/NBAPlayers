package com.example.nbaplayers.network

import com.example.nbaplayers.network.response.PlayerDetailResp
import com.example.nbaplayers.network.response.PlayersListResp
import com.example.nbaplayers.network.response.TeamDetailResp
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API interface for communication with BallDon'tLie.
 */
interface ApiNBA {

    /**
     * Get list of players
     * @param perPage Representing count of items
     * @param cursor Representing next step in pagination
     * @return List of players with meta data for pagination
     */
    @GET("players")
    suspend fun getPlayers(
        @Query("per_page") perPage: Int = 35,
        @Query("cursor") cursor: Int = 0
    ): PlayersListResp

    /**
     * get player detail
     * @param id of player
     * @return player with team
     */
    @GET("players/{id}")
    suspend fun getPlayerById(@Path("id") id: Int): PlayerDetailResp

    /**
     * get team detail
     * @param id of team
     * @return team information
     */
    @GET("teams/{id}")
    suspend fun getTeamByID(@Path("id") id: Int): TeamDetailResp
}