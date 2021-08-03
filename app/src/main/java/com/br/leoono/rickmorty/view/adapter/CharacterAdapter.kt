package com.br.leoono.rickmorty.view.adapter

import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.br.leoono.rickmorty.R
import com.br.leoono.rickmorty.databinding.CharacterListItemBinding
import com.br.leoono.rickmorty.util.DialogUtil
import com.br.leoono.rickmorty.view.fragment.CharacterDetailFragment
import com.br.leoono.rickmorty.view.viewmodel.CharacterItemModel

class CharactersAdapter(
    viewLifecycleOwner: LifecycleOwner,
    originalList: List<CharacterItemModel>?,
    val fragment: Fragment
) :
    SimpleDataBindingRecyclerViewAdapter<CharacterItemModel, CharacterListItemBinding>(
        viewLifecycleOwner,
        originalList,
        R.layout.character_list_item,
        BR.character
    ) {

    override fun onBindViewHolder(
        holder: GenericDataBindingViewHolder<CharacterListItemBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)

        val model = getObjForPosition(position)

        holder.binding.imgCharacter.setOnClickListener {
            val character = model?.character
            if (character != null) {
                DialogUtil.displayFragmentModalDialog(
                    fragment.context,
                    fragment.childFragmentManager,
                    CharacterDetailFragment.newInstance(character.value),
                    false, true
                )
            }
        }
    }
}
