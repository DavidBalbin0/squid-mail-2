package com.david.squid_mail.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_email")
data class Email(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sender: String,
    val subject: String,
    val preview: String,
    var time: String = ""
)
