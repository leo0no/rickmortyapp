package com.br.leoono.rickmorty.view.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.br.leoono.rickmorty.model.Character
import com.br.leoono.rickmorty.view.binding.ObservableViewModel

class CharacterDetailViewModel(
    @get:Bindable
    val character: MutableLiveData<Character?>
) : ObservableViewModel() {

    @get:Bindable
    val id: MutableLiveData<Int?>

    @get:Bindable
    val name: MutableLiveData<String?>

    @get:Bindable
    val imageURI: MutableLiveData<String?>

    @get:Bindable
    val gender: MutableLiveData<String?>

    @get:Bindable
    val specie: MutableLiveData<String?>

    @get:Bindable
    val status: MutableLiveData<String?>

    @get:Bindable
    val origin: MutableLiveData<String?>

    @get:Bindable
    val location: MutableLiveData<String?>

    init {
        id = MutableLiveData(character.value?.id)
        name = MutableLiveData(character.value?.name)
        imageURI = MutableLiveData(character.value?.image)
        gender = MutableLiveData(character.value?.gender)
        specie = MutableLiveData(character.value?.species)
        status = MutableLiveData(character.value?.status)
        origin = MutableLiveData(character.value?.origin?.name)
        location = MutableLiveData(character.value?.location?.name)
    }
}