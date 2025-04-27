package com.example.nbaplayers.model

import com.google.gson.annotations.SerializedName

/**
 * Representing data for Pagination
 * @param perPage Representing count of items
 * @param nextCursor Representing next step in pagination
 */
data class Meta(
    @SerializedName("next_cursor")
    val nextCursor: Int,
    @SerializedName("per_page")
    val perPage: Int
)