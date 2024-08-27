package com.example.learning_andriod.singletons

import com.example.learning_andriod.dao.FakeGmailDao
import com.example.learning_andriod.repository.fake.FakeGmailRepository

object GmailRepository {
    private val gmailRepository = FakeGmailRepository()
    fun getInstance(): FakeGmailDao = gmailRepository
}