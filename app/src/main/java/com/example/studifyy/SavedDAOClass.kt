package com.example.studifyy
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SavedDAOClass {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Data:SavedData)
    @Update
   suspend fun update(Data:SavedData)
    @Delete
   suspend fun delete(Data:SavedData)
    @Query("SELECT * FROM Material")
    fun getAllData():LiveData<List<SavedData>>
}