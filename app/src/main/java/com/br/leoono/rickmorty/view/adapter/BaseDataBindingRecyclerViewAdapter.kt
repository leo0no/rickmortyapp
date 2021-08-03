package com.br.leoono.rickmorty.view.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDataBindingRecyclerViewAdapter<ItemType, DataBindingType : ViewDataBinding>(val mLifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<GenericDataBindingViewHolder<DataBindingType>>() {

    // ---------------------------------------------------------------------------------------------
    // region Instance attributes
    // ---------------------------------------------------------------------------------------------

    val currentBindingObjectArray = SparseArray<ItemType?>()

    //endregion

    // ---------------------------------------------------------------------------------------------
    // region Public methods
    // ---------------------------------------------------------------------------------------------

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenericDataBindingViewHolder<DataBindingType> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DataBindingType>(layoutInflater, viewType, parent, false)
        binding.lifecycleOwner = mLifecycleOwner

        return GenericDataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: GenericDataBindingViewHolder<DataBindingType>,
        position: Int
    ) {
        val bindingObject = getObjForPosition(position)
        val bindingVariableID = getBindingVariableID()
        holder.bind(bindingVariableID, bindingObject)

        //Put into array
        currentBindingObjectArray.append(position, bindingObject)
    }

    abstract fun getBindingVariableID(): Int

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    //endregion

    // ---------------------------------------------------------------------------------------------
    // region Abstract methods
    // ---------------------------------------------------------------------------------------------

    abstract fun getObjForPosition(position: Int): ItemType?

    abstract fun getLayoutIdForPosition(position: Int): Int

    //endregion
}
