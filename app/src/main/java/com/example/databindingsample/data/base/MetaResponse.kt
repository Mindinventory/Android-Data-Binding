package com.example.databindingsample.data.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {
    @SerialName("meta")
    lateinit var meta: MetaResponse
}

@Serializable
data class MetaResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String
)
