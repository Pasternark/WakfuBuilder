package com.example.wakfubuilder.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavItem(
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val title: String
)

data class Builds(
    val nombreBuild: String,
    @DrawableRes val imageResourceId: Int,
    val casco1: Int,
    val coraza1: Int,
    val cinturon1: Int,
    val amuleto1: Int,
    val bota1: Int

)

data class Clases(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)

data class Cascos(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)

data class Corazas(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)

data class Cinturones(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)

data class Amuletos(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)

data class Botas(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
)