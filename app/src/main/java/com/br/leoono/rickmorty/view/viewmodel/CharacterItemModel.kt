package com.br.leoono.rickmorty.view.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.br.leoono.rickmorty.model.Character
import com.br.leoono.rickmorty.view.binding.ObservableViewModel

class CharacterItemModel(
    @get:Bindable
    val character: MutableLiveData<Character?>
) : ObservableViewModel() {

    @get:Bindable
    val id: MutableLiveData<Int?>

    @get:Bindable
    val name: MutableLiveData<String?>

    @get:Bindable
    val imageURI: MutableLiveData<String?>

    init {
        id = MutableLiveData(character.value?.id)
        name = MutableLiveData(character.value?.name)
        imageURI = MutableLiveData(character.value?.image)
    }
}