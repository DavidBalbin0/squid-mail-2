package com.david.squid_mail.components

import android.content.Context
import androidx.lifecycle.ViewModel
import com.david.squid_mail.database.repository.EmailRepository
import com.david.squid_mail.model.Email

class EmailComponentViewModel(context: Context): ViewModel() {
    val emailRepository = EmailRepository(context)


}
