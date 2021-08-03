package com.br.leoono.rickmorty.view.binding

class SimpleDataLoader<T>(private val mLoader: ILoader<T>) : BaseDataLoader<T>() {
    /**
     * Method to handle the data to be loaded
     */
    override suspend fun load(): T {
        return mLoader.loadData()
    }

    @FunctionalInterface
    interface ILoader<T> {
        fun loadData(): T
    }
}