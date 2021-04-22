package com.example.databindingsample.ui.randomcharacter

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.databindingsample.data.randomcharacter.entity.CharacterItem
import com.example.databindingsample.domain.base.UiState
import com.example.databindingsample.domain.randomcharacter.usecase.CharacterUsecase
import com.example.databindingsample.ui.base.BaseViewModel
import com.example.databindingsample.ui.base.SingleLiveEvent
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val characterUsecase: CharacterUsecase
) : BaseViewModel() {

    private val _charactersLiveEvent = SingleLiveEvent<UiState<ArrayList<CharacterItem>>>()
    val randomUserLiveEvent: LiveData<UiState<ArrayList<CharacterItem>>> = _charactersLiveEvent

    fun getCharacters(currentPage: Int) {
        _charactersLiveEvent.value = UiState.Loading()
        characterUsecase.invoke(
            scope = viewModelScope,
            params = CharacterUsecase.PageNumber(currentPage)
        ) {
            it.result(
                { response ->
                    _charactersLiveEvent.value = UiState.Success(response.results)
                }
            ) { throwable ->
                _charactersLiveEvent.value = UiState.Error(throwable)
            }
        }
    }
}