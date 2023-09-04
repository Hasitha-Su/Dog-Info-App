package com.hasitha.daggethilttest2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_table")
data class Dog(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "status") val status: String
)