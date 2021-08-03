package com.br.leoono.rickmorty.api

import com.br.leoono.rickmorty.model.ApiHeader
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    fun getCharacterList () : Call<ApiHeader>

    @GET("character")
    fun getCharacterListPage (@Query("page") pageNumber: Int) : Call<ApiHeader>
}