package com.svitlanamozharovska.agronomist

import com.google.gson.annotations.SerializedName

data class ImageDTO(
    @SerializedName("position")
    val position: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("imageUrl")
    val imageUrl: String
)