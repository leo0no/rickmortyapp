package com.br.leoono.rickmorty.api

import com.br.leoono.rickmorty.model.ApiHeader
import com.br.leoono.rickmorty.model.Character
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    fun getCharacterList () : Call<ApiHeader>
}