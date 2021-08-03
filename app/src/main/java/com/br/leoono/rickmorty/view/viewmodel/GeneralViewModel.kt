package com.br.leoono.rickmorty.view.viewmodel

import android.content.Context
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.br.leoono.rickmorty.model.Character
import com.br.leoono.rickmorty.util.ConnectionUtil
import com.br.leoono.rickmorty.view.binding.ObservableViewModel
import com.br.leoono.rickmorty.view.binding.QueueMutableLiveDataLoader

class GeneralViewModel() : ObservableViewModel() {
    var characterList: MediatorLiveData<List<Character>> = MediatorLiveData()
    val characterDataLoader: QueueMutableLiveDataLoader = QueueMutableLiveDataLoader()
    val characterModelList: MediatorLiveData<List<CharacterItemModel>?> = MediatorLiveData()

    private var showNoConnectionMessage = View.GONE
    private var showList = View.GONE
    private var initiated = false
    private lateinit var context: Context

    fun init(lifecycleOwner: LifecycleOwner, context: Context) {
        if (!initiated) {
            this.context = context
            //Load data
            loadData()

            initiated = true

            characterModelList.addSource(
                characterList,
                { updateCharacterModelList(lifecycleOwner) })
        }
    }

    fun reloadData() {
        loadData()
    }

    @Bindable
    fun getShowNoConnectionMessage(): Int {
        return showNoConnectionMessage
    }

    fun setShowNoConnectionMessage(showMessage: Int): GeneralViewModel? {
        showNoConnectionMessage = showMessage
        notifyPropertyChanged(BR.showNoConnectionMessage)
        return this
    }

    @Bindable
    fun getShowList(): Int {
        return showList
    }

    fun setShowList(showList: Int): GeneralViewModel? {
        this.showList = showList
        notifyPropertyChanged(BR.showList)
        return this
    }

    private fun loadData() {
        if (ConnectionUtil.isConnected(context)) {
            characterDataLoader.load(characterList, CharacterLoader())
            setShowNoConnectionMessage(View.GONE)
            setShowList(View.VISIBLE)
        } else {
            setShowNoConnectionMessage(View.VISIBLE)
            setShowList(View.GONE)
        }

    }

    private fun updateCharacterModelList(lifecycleOwner: LifecycleOwner) {
        val newList = characterList.value?.map {
            val characterItemModel = CharacterItemModel(MutableLiveData(it)).apply {
                init(lifecycleOwner, context)
            }
            characterItemModel
        }?.toList()

        //Propagate changes
        characterModelList?.value = newList
    }
}