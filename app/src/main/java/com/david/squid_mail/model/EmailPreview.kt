package com.david.squid_mail.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class EmailPreview(
    val email: Email,
    var isSelected: MutableState<Boolean> = mutableStateOf(false)
)
