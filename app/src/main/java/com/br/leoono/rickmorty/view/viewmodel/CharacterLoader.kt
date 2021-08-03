package com.br.leoono.rickmorty.view.viewmodel

import com.br.leoono.rickmorty.api.ApiService
import com.br.leoono.rickmorty.api.ServiceBuilder
import com.br.leoono.rickmorty.model.Character
import com.br.leoono.rickmorty.view.binding.BaseDataLoader
import timber.log.Timber

class CharacterLoader : BaseDataLoader<List<Character>?>() {

    override suspend fun load(): List<Character>? {
        val destinationService  = ServiceBuilder.buildService(ApiService::class.java)
        var results: ArrayList<Character>? = ArrayList()
        var requestCall = destinationService.getCharacterList()
        var response = requestCall.execute()

        if (response.isSuccessful) {
            val characterList  = response.body()
            results?.addAll(characterList?.results?.toList()!!)

            for (pageNumber in 2..characterList?.info?.pages!!) {
                requestCall = destinationService.getCharacterListPage(pageNumber)
                response = requestCall.execute()
                if (response.isSuccessful) {
                    results?.addAll(response.body()?.results?.toList()!!)
                }
            }
        } else {
            Timber.d(response.errorBody().toString(), "Error while getting data")
            results = null
        }

        return results
    }
}