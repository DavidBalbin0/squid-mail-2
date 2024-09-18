package com.david.squid_mail.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.david.squid_mail.model.Folder

@Dao
interface FolderDao {

    @Insert
    fun insertFolder(folder: Folder): Long

    @Update
    fun updateFolder(folder: Folder): Int

    @Delete
    fun deleteFolder(folder: Folder): Int

    @Query("SELECT * FROM tb_folder WHERE id = :id")
    fun findById(id: Long): Folder

    @Query("SELECT * FROM tb_folder")
    fun findAll(): List<Folder>

    @Query("SELECT * FROM tb_folder WHERE name = :name LIMIT 1")
    fun findByName(name: String): Folder?
}