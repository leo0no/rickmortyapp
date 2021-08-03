package com.br.leoono.rickmorty.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.br.leoono.rickmorty.R
import com.br.leoono.rickmorty.databinding.FragmentCharacterDetailBinding
import com.br.leoono.rickmorty.model.Character
import com.br.leoono.rickmorty.view.viewmodel.CharacterDetailViewModel

class CharacterDetailFragment: Fragment() {
    private var mBinding: FragmentCharacterDetailBinding? = null
    private var viewModel: CharacterDetailViewModel? = null

    companion object {

        fun newInstance(character: Character?): CharacterDetailFragment {
            val fragment = CharacterDetailFragment()
            val args = Bundle()
            fragment.setArguments(args)
            fragment.viewModel = CharacterDetailViewModel(MutableLiveData(character))
            fragment.mBinding?.notifyChange()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)



        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.character = viewModel
    }
}