package com.example.nbaplayers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val position: String,
    val height: String,
    val weight: String,
    @SerialName("jersey_number")
    val jerseyNumber: String,
    val college: String,
    val country: String,
    @SerialName("draft_year")
    val draftYear: Int,
    @SerialName("draft_round")
    val draftRound: Int,
    @SerialName("draft_number")
    val draftNumber: Int,
    val team: Team
)