package com.example.nbaplayers.model

import com.google.gson.annotations.SerializedName


data class Team (
    val id: Int,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    @SerializedName("full_name")
    val clubName: String,
    val abbreviation: String
)