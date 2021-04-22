package com.example.databindingsample.ui.randomcharacter

import com.example.databindingsample.R
import com.example.databindingsample.common.extensions.safeObserve
import com.example.databindingsample.common.extensions.viewModel
import com.example.databindingsample.data.randomcharacter.entity.CharacterItem
import com.example.databindingsample.databinding.FragmentCharacterBinding
import com.example.databindingsample.domain.base.UiState
import com.example.databindingsample.ui.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class CharacterFragment :
    BaseViewModelFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    private val TAG = "CharacterFragment"

    private val characterAdapter by lazy {
        CharacterAdapter()
    }

    override fun buildViewModel(): CharacterViewModel = viewModel(viewModelFactory)

    override fun getClassName(): String = "CharacterFragment"

    override fun initViews() {
        super.initViews()
        setUpViewModel()
        setUpRecyclerView()
        viewModel.getCharacters(1)
    }

    private fun setUpViewModel() {
        binding.characterViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setUpRecyclerView() {
        with(binding) {
            rvCharacters.adapter = characterAdapter
        }
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        with(viewModel) {
            charactersLiveEvent.safeObserve(viewLifecycleOwner, ::handleCharacterResponse)
        }
    }

    private fun handleCharacterResponse(uiState: UiState<ArrayList<CharacterItem>>) {
        when (uiState) {
            is UiState.Success -> {
                characterAdapter.addAll(uiState.data)
            }
            is UiState.Error -> {
                Timber.e(uiState.throwable)
            }
        }
    }

}