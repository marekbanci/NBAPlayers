package com.example.nbaplayers.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("next_cursor")
    val nextCursor: Int,
    @SerialName("per_page")
    val perPage: Int
)