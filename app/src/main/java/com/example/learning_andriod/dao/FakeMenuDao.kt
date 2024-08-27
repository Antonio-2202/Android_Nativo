package com.example.learning_andriod.dao

import com.example.learning_andriod.domain.MenuItem

interface FakeMenuDao {
    fun getAllMenuItem(): List<MenuItem>
}