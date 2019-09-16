package com.svitlanamozharovska.agronomist

import com.google.gson.annotations.SerializedName

data class AccountData(
    @SerializedName("discussionId")
    val discussionId: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("lastAgrotechnicalAction")
    val lastAgrotechnicalAction: String,
    @SerializedName("plantPredecessor")
    val plantPredecessor: String,
    @SerializedName("creatorName")
    val creatorName: String,
    @SerializedName("iconUrl")
    val iconUrl: String,
    @SerializedName("rainfall")
    val rainfall: Int,
    @SerializedName("cultureId")
    val cultureId: Int,
    @SerializedName("problemId")
    val problemId: Int,
    @SerializedName("creatorId")
    val creatorId: Int,
    @SerializedName("localityTypeId")
    val localityTypeId: Int,
    @SerializedName("createDateUNIX")
    val createDateUNIX: Long,
    @SerializedName("commentsAmount")
    val commentsAmount: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("daytimeTemperature")
    val daytimeTemperature: Double,
    @SerializedName("nightTemperature")
    val nightTemperature: Double,
    @SerializedName("imageDTOList")
    val imageDTOList: ArrayList<ImageDTO>,
    @SerializedName("expectedImageAmount")
    val expectedImageAmount: Int,
    @SerializedName("public")
    val public: Boolean,
    @SerializedName("promo")
    val promo: Boolean


)