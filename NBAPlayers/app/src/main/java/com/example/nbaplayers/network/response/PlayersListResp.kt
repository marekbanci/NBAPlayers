package com.example.nbaplayers.network.response

import com.example.nbaplayers.model.Meta
import com.example.nbaplayers.model.Player

data class PlayersListResp(
    val data: List<Player>,
    val meta: Meta
)