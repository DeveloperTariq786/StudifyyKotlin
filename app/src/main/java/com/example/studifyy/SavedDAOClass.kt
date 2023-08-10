package com.example.studifyy
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface SavedDAOClass {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Data:SavedData)
    @Query("SELECT * FROM Material")
    fun getAllData():LiveData<List<SavedData>>
}