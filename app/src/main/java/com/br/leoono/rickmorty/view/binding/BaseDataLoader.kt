package com.br.leoono.rickmorty.view.binding

abstract class BaseDataLoader<T> : IDataLoader<T> {

    private var mIsLoading = false

    override fun onPreLoad() {
        mIsLoading = true
    }

    override fun onFinishLoad(result: T?) {
        mIsLoading = false
    }
}