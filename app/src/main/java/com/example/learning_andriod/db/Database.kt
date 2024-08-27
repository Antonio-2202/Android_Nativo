package com.example.learning_andriod.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learning_andriod.constants.AppConstants
import com.example.learning_andriod.db.dao.GmailDao
import com.example.learning_andriod.db.entities.Gmail

@Database(entities = [Gmail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gmailDao(): GmailDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppConstants.ROOM_DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}