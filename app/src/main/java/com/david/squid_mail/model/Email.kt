package com.david.squid_mail.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity(
    tableName = "tb_email",
    foreignKeys = [
        ForeignKey(
            entity = Folder::class,
            parentColumns = ["id"],
            childColumns = ["folderId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Email(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sender: String,
    val receiver: String,
    val subject: String,
    val content: String,
    var time: Date = Date(),
    var isRead : Boolean = false,
    var isFavorite: Boolean = false,
    val isDraft: Boolean = false,
    val isSpam: Boolean = false,
    var isArchived: Boolean = false,
    val folderId: Long? = null,
)
