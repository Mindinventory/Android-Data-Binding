package com.example.databindingsample.data.randomcharacter.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.databindingsample.data.randomcharacter.entity.RandomCharacterResponse
import com.example.databindingsample.domain.randomcharacter.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi
) : CharacterRepository {

    private val TAG = "CharacterRepositoryImpl"

    @SuppressLint("LogNotTimber")
    override suspend fun getCharacters(pageNumber: Int): RandomCharacterResponse {
        Log.d(TAG, "getCharacters: " + characterApi.getCharacters(pageNumber).results[0].name)
        return characterApi.getCharacters(pageNumber)
    }
}