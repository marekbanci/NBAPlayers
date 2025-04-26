package com.example.nbaplayers.network.response

import com.example.nbaplayers.model.Meta
import com.example.nbaplayers.model.Player
import kotlinx.serialization.Serializable

@Serializable
data class PlayersListResp(
    val data: List<Player>,
    val meta: Meta
)