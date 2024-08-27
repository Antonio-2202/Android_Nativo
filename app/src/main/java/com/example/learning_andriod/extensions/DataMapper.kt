package com.example.learning_andriod.extensions

import com.example.learning_andriod.db.entities.Gmail
import com.example.learning_andriod.domain.GmailItem
import java.util.Date

class DataMapper {
    fun toUi(item: Gmail) = with(item) {
        GmailItem(
            id,
            subtitle,
            sender,
            content,
            Date(date)
        )
    }

    fun toDatabase(item: GmailItem) = with(item) {
        Gmail(
            id,
            subtitle,
            sender,
            content,
            date.time,
            isActive = true
        )
    }
}