package com.example.meucartaodevisitas.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String?,
    val htmlUrl: String
)
