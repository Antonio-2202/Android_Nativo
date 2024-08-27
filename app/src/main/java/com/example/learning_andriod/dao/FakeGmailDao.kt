package com.example.learning_andriod.dao

import com.example.learning_andriod.domain.GmailItem

interface FakeGmailDao {
    fun getAllGmail(): List<GmailItem>
    fun createNewGmail(item: GmailItem)
}