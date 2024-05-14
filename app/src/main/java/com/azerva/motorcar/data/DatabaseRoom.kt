package com.azerva.motorcar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.azerva.motorcar.model.CarDao
import com.azerva.motorcar.model.CarVo

@Database( version = 1, exportSchema = false,
    entities = [CarVo::class])
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun carDao() : CarDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseRoom? = null

        fun getInstance(context: Context): DatabaseRoom {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseRoom::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}