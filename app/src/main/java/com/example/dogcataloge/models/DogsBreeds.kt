package com.example.dogcataloge.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogsList")
data class DogsBreeds(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val message: String?,
    val status:String?)
