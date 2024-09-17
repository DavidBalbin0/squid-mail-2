package com.david.squid_mail.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.david.squid_mail.model.Email

@Dao
interface EmailDao {
    @Insert
    fun insertEmail(email: Email): Long

    @Update
    fun updateEmail(email: Email): Int

    @Delete
    fun deleteEmail(email: Email): Int

    @Query("SELECT * FROM tb_email WHERE id = :id")
    fun findById(id: Long): Email

    @Query("SELECT * FROM tb_email")
    fun findAll(): List<Email>

    @Query("SELECT * FROM tb_email WHERE isDraft = 0 AND isArchived = 0 AND isSpam = 0")
    fun findAllToInbox(): List<Email>
}

