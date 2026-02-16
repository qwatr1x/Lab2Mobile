package com.example.secondlab.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Artwork(
    @DrawableRes val imageResId: Int,
    @StringRes val titleResId: Int,
    @StringRes val authorResId: Int,
    @StringRes val yearResId: Int,
    @StringRes val descriptionResId: Int
)
