package com.example.databindingsample.domain.randomcharacter.repository

import com.example.databindingsample.data.randomcharacter.entity.RandomCharacterResponse

interface CharacterRepository {
    suspend fun getCharacters(pageNumber : Int) : RandomCharacterResponse
}