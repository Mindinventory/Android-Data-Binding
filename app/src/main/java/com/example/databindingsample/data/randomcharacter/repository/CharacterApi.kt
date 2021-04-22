package com.example.databindingsample.data.randomcharacter.repository

import com.example.databindingsample.data.randomcharacter.entity.RandomCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") pageNumber: Int): RandomCharacterResponse
}