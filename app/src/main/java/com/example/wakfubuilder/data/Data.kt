package com.example.wakfubuilder.data

import com.example.wakfubuilder.R
import com.example.wakfubuilder.model.Amuletos
import com.example.wakfubuilder.model.Botas
import com.example.wakfubuilder.model.Builds
import com.example.wakfubuilder.model.Cascos
import com.example.wakfubuilder.model.Cinturones
import com.example.wakfubuilder.model.Clases
import com.example.wakfubuilder.model.Corazas
import com.example.wakfubuilder.model.Favoritos

class Datasource() {
    fun loadBuilds(): List<Builds> {
        return mutableListOf<Builds>(
        Builds("Zurcarák de críticos", R.drawable.clase1,
            R.drawable.casco1, R.drawable.coraza1, R.drawable.cinturon1,
            R.drawable.amuleto1, R.drawable.bota1),
        Builds("Anutrof de melé", R.drawable.clase2,
            R.drawable.casco2, R.drawable.coraza2, R.drawable.cinturon2,
            R.drawable.amuleto2, R.drawable.bota2),
        Builds("Feca de escudos", R.drawable.clase3,
            R.drawable.casco3, R.drawable.coraza3, R.drawable.cinturon3,
            R.drawable.amuleto3, R.drawable.bota3),
        Builds("Zurcarák de melé", R.drawable.clase1,
            R.drawable.casco1, R.drawable.coraza2, R.drawable.cinturon3,
            R.drawable.amuleto1, R.drawable.bota2),
        Builds("Anutrof de drop", R.drawable.clase2,
            R.drawable.casco3, R.drawable.coraza1, R.drawable.cinturon2,
            R.drawable.amuleto3, R.drawable.bota1))
    }

    fun loadClases(): List<Clases> {
        return listOf<Clases>(
        Clases(R.string.clase1, R.drawable.clase1),
        Clases(R.string.clase2, R.drawable.clase2),
        Clases(R.string.clase3, R.drawable.clase3))
    }

    fun loadCascos(): List<Cascos> {
        return listOf<Cascos>(
        Cascos(R.string.casco1, R.drawable.casco1),
        Cascos(R.string.casco2, R.drawable.casco2),
        Cascos(R.string.casco3, R.drawable.casco3))
    }

    fun loadCorazas(): List<Corazas> {
        return listOf<Corazas>(
        Corazas(R.string.corazas1, R.drawable.coraza1),
        Corazas(R.string.corazas2, R.drawable.coraza2),
        Corazas(R.string.corazas3, R.drawable.coraza3))
    }

    fun loadCinturones(): List<Cinturones> {
        return listOf<Cinturones>(
        Cinturones(R.string.cinturon1, R.drawable.cinturon1),
        Cinturones(R.string.cinturon2, R.drawable.cinturon2),
        Cinturones(R.string.cinturon3, R.drawable.cinturon3))
    }

    fun loadAmuletos(): List<Amuletos> {
        return listOf<Amuletos>(
        Amuletos(R.string.amuleto1, R.drawable.amuleto1),
        Amuletos(R.string.amuleto2, R.drawable.amuleto2),
        Amuletos(R.string.amuleto3, R.drawable.amuleto3))
    }

    fun loadBotas(): List<Botas> {
        return listOf<Botas>(
        Botas(R.string.bota1, R.drawable.bota1),
        Botas(R.string.bota2, R.drawable.bota2),
        Botas(R.string.bota3, R.drawable.bota3))
    }

    fun loadFavorites(): List<Favoritos>{
        return mutableListOf<Favoritos>(

        )
    }
}
