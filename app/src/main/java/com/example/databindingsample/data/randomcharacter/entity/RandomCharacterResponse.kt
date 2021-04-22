package com.example.databindingsample.data.randomcharacter.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class RandomCharacterResponse(
    @SerialName("results")
    val results: ArrayList<CharacterItem>
)

@Serializable
data class CharacterItem(
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("status")
    val status: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("species")
    val species: String,
)

