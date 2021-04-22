package com.example.databindingsample.ui.randomcharacter

import android.view.View
import com.example.databindingsample.R
import com.example.databindingsample.common.extensions.safeObserve
import com.example.databindingsample.common.extensions.viewModel
import com.example.databindingsample.data.randomcharacter.entity.CharacterItem
import com.example.databindingsample.databinding.FragmentCharacterBinding
import com.example.databindingsample.domain.base.UiState
import com.example.databindingsample.ui.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CharacterFragment :
    BaseViewModelFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    private val characterAdapter by lazy {
        CharacterAdapter()
    }

    override fun buildViewModel(): CharacterViewModel = viewModel(viewModelFactory)

    override fun getClassName(): String = "CharacterFragment"

    override fun initViews() {
        super.initViews()
        setUpRecyclerView()
        viewModel.getCharacters(1)
    }

    private fun setUpRecyclerView() {
        with(binding) {
            rvCharacters.adapter = characterAdapter
        }
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        with(viewModel) {
            randomUserLiveEvent.safeObserve(viewLifecycleOwner, ::handleCharacterResponse)
        }
    }

    private fun handleCharacterResponse(uiState: UiState<ArrayList<CharacterItem>>) {
        when (uiState) {
            is UiState.Loading -> {
                binding.pbCharactersLoading.visibility = View.VISIBLE
            }
            is UiState.Success -> {
                if (uiState.data.isEmpty()) {
                    binding.tvContentStatus.visibility = View.VISIBLE
                } else {
                    binding.tvContentStatus.visibility = View.GONE
                    characterAdapter.addAll(uiState.data)
                }
                binding.pbCharactersLoading.visibility = View.GONE
            }
            is UiState.Error -> {
                binding.pbCharactersLoading.visibility = View.GONE
            }
        }
    }


}