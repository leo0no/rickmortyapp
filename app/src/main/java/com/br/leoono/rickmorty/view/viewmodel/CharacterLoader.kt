package com.br.leoono.rickmorty.view.viewmodel

import com.br.leoono.rickmorty.api.ApiService
import com.br.leoono.rickmorty.api.ServiceBuilder
import com.br.leoono.rickmorty.model.Character
import com.br.leoono.rickmorty.view.binding.BaseDataLoader
import timber.log.Timber

class CharacterLoader : BaseDataLoader<List<Character>?>() {

    override suspend fun load(): List<Character>? {
        val destinationService  = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall = destinationService.getCharacterList()
        val response = requestCall.execute()
        return if (response.isSuccessful) {
            val characterList  = response.body()

            characterList?.results
        } else {
            Timber.d(response.errorBody().toString(), "Error while getting data")
            null
        }
    }
}