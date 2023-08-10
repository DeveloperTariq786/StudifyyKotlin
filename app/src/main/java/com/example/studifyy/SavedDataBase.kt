package com.example.studifyy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [SavedData::class], version = 1, exportSchema = false)
abstract class SavedDataBase: RoomDatabase() {
    abstract fun savedDaoClass():SavedDAOClass
    companion object{
        @Volatile
        private var INSTANCE: SavedDataBase?=null
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): SavedDataBase {
        return INSTANCE ?: synchronized(this) {
              val instant=Room.databaseBuilder(
                  context.applicationContext,
                  SavedDataBase::class.java,
                  "OffLineDataBase"
              ).build()
              INSTANCE=instant
              instant
          }
        }

    }
}