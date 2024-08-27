package com.example.learning_andriod.dao

import com.example.learning_andriod.domain.PhotoItem

interface FakePhotoDao {
    fun getAllPhotos(): List<PhotoItem>
    fun addPhoto(item: PhotoItem)
}