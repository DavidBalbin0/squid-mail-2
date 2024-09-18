package com.david.squid_mail.database.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.david.squid_mail.database.dao.EmailDb
import com.david.squid_mail.model.Email
import com.david.squid_mail.model.FolderType

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

    fun findById(id: Long): Email {
        return emailDao.findById(id)
    }

    fun findAllByFolderType(folderType: FolderType, folderId: Long?): List<Email> {
        return when(folderType) {
            FolderType.INBOX -> emailDao.findAllToInbox()
            FolderType.SENT -> emailDao.findAllToSent(0)
            FolderType.DRAFTS -> emailDao.findAllToDrafts()
            FolderType.ARCHIVED -> emailDao.findAllToArchived()
            FolderType.FAVORITES -> emailDao.findAllToFavorites()
            FolderType.SPAM -> emailDao.findAllToSpam()
            FolderType.TRASH -> emailDao.findAllToTrash()
            FolderType.OTHER -> findAllToOther(folderId)

        }
    }

    private fun findAllToOther(folderId: Long?): List<Email> {
        return if (folderId == null) {
            Log.e("EmailRepository", "FolderId is null")
            emptyList()
        } else {
            emailDao.findAllToOther(folderId)
        }
    }

    fun findAllToInbox(): List<Email> {
        return emailDao.findAllToInbox()
    }
}
