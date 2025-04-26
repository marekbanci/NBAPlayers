package com.example.nbaplayers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team (
    val id: Int,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    @SerialName("full_name")
    val clubName: String,
    val abbreviation: String
)