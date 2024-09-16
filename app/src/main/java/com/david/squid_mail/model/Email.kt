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
    val isRead : Boolean = false,
    val isFavorite: Boolean = false,

    val folderId: Long,
)
