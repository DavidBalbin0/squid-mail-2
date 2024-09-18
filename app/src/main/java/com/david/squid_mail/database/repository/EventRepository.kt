package com.david.squid_mail.database.repository

import android.content.Context
import com.david.squid_mail.database.dao.EmailDb
import com.david.squid_mail.model.Email
import com.david.squid_mail.model.Event
import kotlinx.coroutines.flow.Flow

import java.time.LocalDate

class EventRepository(context: Context) {
    private val emailDb = EmailDb.getInstance(context)
    private val eventDao = emailDb.eventDao()
    fun insertEvent(event: Event): Long {
        return eventDao.insertEvent(event)
    }

    fun updateEvent(event: Event) {
        eventDao.updateEvent(event)
    }

    fun deleteEvent(eventId: Long) {
        eventDao.deleteEvent(eventId)
    }

    fun getEventsForDate(date: LocalDate): Flow<List<Event>> {
        return eventDao.getEventsForDate(date)
    }
}
