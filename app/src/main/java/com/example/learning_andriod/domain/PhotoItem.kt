package com.example.learning_andriod.domain

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoItem(
    val id: String,
    val photoUrl: Bitmap
) : Parcelable
