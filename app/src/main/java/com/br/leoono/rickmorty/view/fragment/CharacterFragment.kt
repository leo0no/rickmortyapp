package com.br.leoono.rickmorty.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.leoono.rickmorty.R
import com.br.leoono.rickmorty.databinding.CharacterListBinding
import com.br.leoono.rickmorty.view.adapter.CharactersAdapter
import com.br.leoono.rickmorty.view.viewmodel.CharacterItemModel
import com.br.leoono.rickmorty.view.viewmodel.GeneralViewModel

class CharacterFragment : Fragment(){

    private lateinit var binding: CharacterListBinding
    private var viewModel: GeneralViewModel? = null

    companion object {

        fun newInstance(): CharacterFragment {
            val fragment = CharacterFragment()
            val args = Bundle()
            fragment.setArguments(args)

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        setupObservers()

        binding =
            DataBindingUtil.inflate(inflater, R.layout.character_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)
        viewModel?.init(this, this.requireContext())
    }

    fun refreshData() {
        viewModel?.reloadData()
    }

    private fun setupAdapter(characterList: List<CharacterItemModel>?) {
        binding.itemListRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = CharactersAdapter(viewLifecycleOwner, characterList, this)
        binding.itemListRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel?.characterModelList?.observe(
            viewLifecycleOwner,
            Observer {
                viewModel?.setShowLoading(View.GONE)
                    setupAdapter(it)
            }
        )
    }
}