package com.example.assignment.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class EntityModel (
    @PrimaryKey(autoGenerate = true)
    val uid: Int=0,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "age")
    val age: Int,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "city")
    val city: String


    )