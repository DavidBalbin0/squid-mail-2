package com.david.squid_mail.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.david.squid_mail.model.Event
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface EventDao {
    @Query("SELECT * FROM tb_event WHERE date = :date")
    fun getEventsForDate(date: LocalDate): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: Event): Long

    @Update
    fun updateEvent(event: Event)

    @Query("DELETE FROM tb_event WHERE id = :eventId")
    fun deleteEvent(eventId: Long)
}
