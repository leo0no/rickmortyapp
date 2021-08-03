package com.br.leoono.rickmorty.view.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class GenericDataBindingViewHolder<DataBindingType : ViewDataBinding>(val binding: DataBindingType) :
    RecyclerView.ViewHolder(binding.root) {

    // ---------------------------------------------------------------------------------------------
    // region Public methods
    // ---------------------------------------------------------------------------------------------

    fun bind(itemData: Int, bindingObject: Any?) {
        setBindingVariable(itemData, bindingObject)
        binding.executePendingBindings()
    }

    fun setBindingVariable(itemData: Int, bindingObject: Any?) {
        binding.setVariable(itemData, bindingObject)
    }

    //endregion
}
