package com.david.squid_mail.database.repository

import android.content.Context
import com.david.squid_mail.database.dao.EmailDb
import com.david.squid_mail.model.Folder

class FolderRepository(context: Context) {

    private val folderDb = EmailDb.getInstance(context)
    private val folderDao = folderDb.folderDao()

    fun insertFolder(folder: Folder): Long {
        return folderDao.insertFolder(folder)
    }

    fun updateFolder(folder: Folder): Int {
        return folderDao.updateFolder(folder)
    }

    fun deleteFolder(folder: Folder): Int {
        return folderDao.deleteFolder(folder)
    }

    fun findById(id: Long): Folder {
        return folderDao.findById(id)
    }

    fun findAll(): List<Folder> {
        return folderDao.findAll()
    }

    fun findByName(name: String): Folder? {
        return folderDao.findByName(name)
    }
}