package com.example.databindingsample.domain.randomcharacter.usecase

import com.example.databindingsample.data.randomcharacter.entity.RandomCharacterResponse
import com.example.databindingsample.domain.randomcharacter.repository.CharacterRepository
import com.example.databindingsample.domain.base.BaseUseCase
import javax.inject.Inject

class CharacterUsecase @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseUseCase<RandomCharacterResponse, CharacterUsecase.PageNumber>() {

    data class PageNumber(val pageNumber : Int)

    override suspend fun execute(params: PageNumber): RandomCharacterResponse {
        return characterRepository.getCharacters(params.pageNumber)
    }

}