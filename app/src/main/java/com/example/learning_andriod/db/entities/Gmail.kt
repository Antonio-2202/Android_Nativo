package com.example.learning_andriod.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "gmail_table")
data class Gmail(
    @PrimaryKey(autoGenerate = false) val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "sender") val sender: String,
    @ColumnInfo(name = "subtitle") val subtitle: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "isActive") val isActive: Boolean
)
