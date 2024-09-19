package com.david.squid_mail.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.david.squid_mail.database.Converters
import com.david.squid_mail.model.Email

import com.david.squid_mail.model.Folder

@Database(entities = [Email::class, Folder::class], version =6)
@TypeConverters(Converters::class)
abstract class EmailDb : RoomDatabase(){
    abstract fun emailDao(): EmailDao
    abstract fun folderDao(): FolderDao


            companion object {
        const val DB_NAME = "app_db"

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