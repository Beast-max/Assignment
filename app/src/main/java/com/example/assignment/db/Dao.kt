package com.example.assignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment.db.EntityModel

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend  fun getAll(): List<EntityModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: EntityModel)

}