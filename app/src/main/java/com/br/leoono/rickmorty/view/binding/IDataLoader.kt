package com.br.leoono.rickmorty.view.binding

interface IDataLoader<T> {
    fun onPreLoad()

    suspend fun load(): T?

    fun onFinishLoad(result: T?)
}