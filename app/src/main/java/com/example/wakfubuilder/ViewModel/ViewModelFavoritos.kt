package com.example.wakfubuilder.ViewModel

import androidx.lifecycle.ViewModel
import com.example.wakfubuilder.data.Datasource
import com.example.wakfubuilder.model.Builds
import kotlinx.coroutines.flow.MutableStateFlow

class ViewModelFavoritos : ViewModel(){
    private val datasource = Datasource()

    private var favorites = MutableStateFlow<List<ViewModel>>(emptyList())
    val favoritos: MutableStateFlow<List<ViewModel>> get() = favorites

    fun search(query: String): List<Builds> {
        val buildPorNombre = datasource

        val result = mutableListOf<Builds>()
        result.addAll(buildPorNombre.loadBuilds().filter { it.nombreBuild.contains(query, ignoreCase = true) })
        return result
    }

    fun isFavorite(){


    }

    fun addFavorite(){

    }

    fun removeFavorite() {

    }

}

