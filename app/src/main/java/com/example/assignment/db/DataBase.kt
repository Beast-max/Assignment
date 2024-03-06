package com.example.assignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [EntityModel::class], version = 2)
abstract class DataBase : RoomDatabase() {

    abstract fun Dao(): UserDao

}