package com.example.studifyy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Material")
data class SavedData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val TopicName: String,
    val url: String
)
