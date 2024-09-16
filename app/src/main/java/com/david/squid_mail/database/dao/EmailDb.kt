package com.david.squid_mail.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.david.squid_mail.model.Email

@Database(entities = [Email::class], version = 1)
abstract class EmailDb : RoomDatabase(){
    abstract fun emailDao(): EmailDao

    companion object {
        const val DB_NAME = "email_db"

        private lateinit var instance: EmailDb

        fun getInstance(context: Context): EmailDb {
            if (!::instance.isInitialized) {
                synchronized(EmailDb::class) {
                    instance = Room.databaseBuilder(
                        context,
                        EmailDb::class.java,
                        DB_NAME
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }

}