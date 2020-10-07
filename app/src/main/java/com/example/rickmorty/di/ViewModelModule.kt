package com.example.rickmorty.di

import com.example.rickmorty.ui.characterdetail.CharacterDetailViewModel
import com.example.rickmorty.ui.characters.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CharactersViewModel(get())
    }

    viewModel { (characterId: Int) ->
        CharacterDetailViewModel(get(), characterId)
    }
}