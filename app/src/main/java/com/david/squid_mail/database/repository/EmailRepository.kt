package com.david.squid_mail.database.repository

import android.content.Context
import com.david.squid_mail.database.dao.EmailDb
import com.david.squid_mail.model.Email

class EmailRepository (context: Context) {

    private val emailDb = EmailDb.getInstance(context)
    private val emailDao = emailDb.emailDao()

    fun insertEmail(email: Email): Long {
        return emailDao.insertEmail(email)
    }

    fun updateEmail(email: Email): Int {
        return emailDao.updateEmail(email)
    }

    fun deleteEmail(email: Email): Int {
        return emailDao.deleteEmail(email)
    }

    fun findById(id: String): Email {
        return emailDao.findById(id)
    }

    fun findAll(): List<Email> {
        return emailDao.findAll()
    }
}
