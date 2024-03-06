package com.example.assignment

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignment.db.DataBase

class MyApp : Application() {

    companion object {
        lateinit var database: DataBase
    }

    override fun onCreate() {
        super.onCreate()
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users ADD COLUMN city TEXT");
            }

        }
        database = Room.databaseBuilder(
            applicationContext,
            DataBase::class.java,
            "empolyeedb"
        ).addMigrations(MIGRATION_1_2)
        .build()
    }
}