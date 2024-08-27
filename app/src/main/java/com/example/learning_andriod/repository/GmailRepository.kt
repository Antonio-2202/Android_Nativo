package com.example.learning_andriod.repository

import com.example.learning_andriod.db.dao.GmailDao
import com.example.learning_andriod.db.entities.Gmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GmailRepository(private val gmailDao: GmailDao) {

    fun insertGmail(gmail: Gmail) {
        CoroutineScope(Dispatchers.IO).launch {
            gmailDao.insertGmail(gmail)
        }
    }

    fun insertAllGmail(gmailList: List<Gmail>) {
        CoroutineScope(Dispatchers.IO).launch {
            gmailDao.insertAllGmail(gmailList)
        }
    }

    fun getGmailById(gmailId: String, callback: (Gmail?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val gmail = gmailDao.getGmailById(gmailId)
            callback(gmail)
        }
    }

    fun getAllGmail(callback: (List<Gmail>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val gmailItems = gmailDao.getAllGmail()
            callback(gmailItems)
        }
    }

    fun deleteAllGmail() {
        CoroutineScope(Dispatchers.IO).launch {
            gmailDao.deleteAllGmail()
        }
    }

}