package com.example.learning_andriod.singletons

import com.example.learning_andriod.dao.FakePhotoDao
import com.example.learning_andriod.repository.fake.FakePhotoRepository

object PhotoRepository {
    private val photoRepository = FakePhotoRepository()
    fun getInstance(): FakePhotoDao = photoRepository
}