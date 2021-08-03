package com.br.leoono.rickmorty

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.br.leoono.rickmorty.view.fragment.CharacterFragment

class MainActivity : AppCompatActivity() {

    val characterFragemnt = CharacterFragment.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .add(R.id.content, characterFragemnt)
            .commit()
    }

    fun refreshData(view: View?) {
        characterFragemnt.refreshData()
    }
}