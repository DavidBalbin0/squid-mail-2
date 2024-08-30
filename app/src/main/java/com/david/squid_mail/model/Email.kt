package com.david.squid_mail.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Email(
    val id: String,
    val sender: String,
    val subject: String,
    val preview: String,
    var isSelected: MutableState<Boolean> = mutableStateOf(false),
    var time: String
)
