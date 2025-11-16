package com.example.meucartaodevisitas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meucartaodevisitas.data.model.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects ORDER BY name")
    fun getAll(): Flow<List<Project>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(projects: List<Project>)

    @Query("DELETE FROM projects")
    suspend fun clearAll()

    @Query("SELECT * FROM projects WHERE id = :id LIMIT 1")
    fun getById(id: Long): Flow<Project?>
}
