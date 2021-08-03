package com.br.leoono.rickmorty.view.adapter

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner

open class SimpleDataBindingRecyclerViewAdapter<ItemType, BindingType : ViewDataBinding>(
    lifecycleOwner: LifecycleOwner,
    private val mOriginalList: List<ItemType>?,
    private val mLayoutID: Int,
    private val mItemBindingVariableID: Int = BR.character
) : BaseDataBindingRecyclerViewAdapter<ItemType, BindingType>(lifecycleOwner) {

    override fun getBindingVariableID(): Int {
        return mItemBindingVariableID
    }

    override fun getItemCount(): Int {
        return mOriginalList?.size ?: 0
    }

    override fun getObjForPosition(position: Int): ItemType? {
        return mOriginalList?.get(position)
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return mLayoutID
    }
}

