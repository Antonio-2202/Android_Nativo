package com.example.learning_andriod.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learning_andriod.db.entities.Gmail

@Dao
interface GmailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGmail(gmail: Gmail)

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    suspend fun insertAllGmail(gmailList: List<Gmail>)

    @Query("SELECT * FROM gmail_table WHERE id = :gmailId")
    suspend fun getGmailById(gmailId: String): Gmail?

    @Query("SELECT * FROM gmail_table")
    suspend fun getAllGmail(): List<Gmail>

    @Query("DELETE FROM GMAIL_TABLE")
    suspend fun deleteAllGmail(): Void
}