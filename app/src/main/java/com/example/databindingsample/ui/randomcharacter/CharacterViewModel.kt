package com.example.databindingsample.ui.randomcharacter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    val charactersLiveEvent: LiveData<UiState<ArrayList<CharacterItem>>> = _charactersLiveEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _contentStatus = MutableLiveData<Int>()
    val contentStatus: LiveData<Int> = _contentStatus

    fun getCharacters(currentPage: Int) {
        _isLoading.value = true
        characterUsecase.invoke(
            scope = viewModelScope,
            params = CharacterUsecase.PageNumber(currentPage)
        ) {
            it.result(
                { response ->
                    _contentStatus.value = response.results.size
                    _charactersLiveEvent.value = UiState.Success(response.results)
                    _isLoading.value = false
                }
            ) { throwable ->
                _charactersLiveEvent.value = UiState.Error(throwable)
                _isLoading.value = false
            }
        }
    }
}