package com.example.learning_andriod.repository.fake

import com.example.learning_andriod.dao.FakePhotoDao
import com.example.learning_andriod.domain.PhotoItem

class FakePhotoRepository: FakePhotoDao {
    private var fakePhotos: MutableList<PhotoItem> = mutableListOf()

    override fun getAllPhotos(): List<PhotoItem> {
        return fakePhotos
    }

    override fun addPhoto(item: PhotoItem) {
        if(fakePhotos.none{ it.id == item.id}) {
            fakePhotos.add(item)
        }
    }
}