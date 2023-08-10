package com.example.studifyy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Material")
data class SavedData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo
    val TopicName: String,
    @ColumnInfo
    val url: String
)
